package ru.ivanov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.classes.Terminal.TerminalServer;
import ru.ivanov.dao.AccountDAO;
import ru.ivanov.models.Account;
import ru.ivanov.models.Sum;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/session")
@ComponentScan(basePackages = {"ru.ivanov"})
public class AccountController {

    private final AccountDAO accountDAO;
    private final TerminalServer terminalServer;
    private Account currentAccount;

    @Autowired
    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
        this.terminalServer = new TerminalServer(accountDAO.toHashSet());
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("account", new Account());
        return "account/validate_account";
    }

    @GetMapping("/operations")
    public String operations(Model model) {
        List<String> operations = new ArrayList<>();
        operations.add("Put money");
        operations.add("Take money");
        operations.add("Check statement");
        model.addAttribute("operations", operations);
        model.addAttribute("account", currentAccount);
        return "account/list_operations";
    }

    @PostMapping()
    public String validateAccount(@ModelAttribute("account") Account account, Model model) {
        if (accountDAO.validate(account)) {
            currentAccount = account;
            List<String> operations = new ArrayList<>();
            operations.add("Put money");
            operations.add("Take money");
            operations.add("Check statement");
            model.addAttribute("operations", operations);
            model.addAttribute("account", currentAccount);
            return "account/list_operations";
        }
        return "account/error_validation";
    }

    @GetMapping("/{operation}")
    public String showOperation(@PathVariable("operation") String operation,
                                Model model) {
        switch (operation) {
            case ("Put money"): {
                model.addAttribute("account", currentAccount);
                model.addAttribute("sum", new Sum());
                return "account/put_money";
            }
            case ("Take money"): {
                model.addAttribute("account", currentAccount);
                model.addAttribute("sum", new Sum());
                return "account/take_money";
            }
            case ("Check statement"): {
                model.addAttribute("account", currentAccount);
                return "account/check_statement";
            }
        }
        return "redirect:/session";
    }

    @PostMapping("/put-money")
    public String putMoneyForAccount(@ModelAttribute("sum") Sum sum) {
        if (sum.getSum() % 100 != 0) {
            return "account/put_money";
        }
        terminalServer.putMoney(currentAccount.getLogin(), sum.getSum());
        return "account/success_operations";
    }

    @PostMapping("/take-money")
    public String takeMoneyForAccount(@ModelAttribute("sum") Sum sum) {
        if (sum.getSum() % 100 != 0) {
            return "account/put_money";
        }
        terminalServer.takeMoney(currentAccount.getLogin(), sum.getSum());
        return "account/success_operations";
    }
}
