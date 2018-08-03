package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.*;

@Controller
public class HomeController {
    @Autowired
    BankAccountRepository bankAccountRepository;


    @RequestMapping("/base")
    public String baseForm(Model model)
    {
        model.addAttribute("bankaccountbase");
        return "baseform";
    }
    @RequestMapping("/")
        public String transactionForm(Model model)
        {
            model.addAttribute("bankaccounts" ,bankAccountRepository.findAll());

            return "transactionhistoryform";
        }
    @GetMapping("/deposit")
    public String depositForm(Model model)
    {
        model.addAttribute("bankaccount" , new BankAccount());
        return "depositform";
    }
    @GetMapping("/withdrawal")
    public String withdrawalForm(Model model)
    {
        model.addAttribute("bankaccount" , new BankAccount());
        return "withdrawalform";
    }


    @PostMapping("/process")
    public String processForm (@Valid BankAccount bankaccount , BindingResult result)
    {
        if (result.hasErrors())
        {
            return "depositform";
        }
        bankAccountRepository.save(bankaccount);
        return "redirect:/";
    }


/*  @RequestMapping("/update/{accountnumber}")
    public String balanceForm(@PathVariable("accountnumber") long accountnumber , Model model) throws SQLException {
        model.addAttribute("bankaccount",bankAccountRepository.findById(accountnumber).get());
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:h2:mem:testdb";
        String user = "";
        String password = "";
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM BANK_ACCOUNT where ACCOUNTNUMBER =" + accountnumber);

        return "balanceform";
    }*/

}
