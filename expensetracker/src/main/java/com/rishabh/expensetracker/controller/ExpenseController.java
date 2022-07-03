package com.rishabh.expensetracker.controller;

import com.rishabh.expensetracker.entity.Expense;
import com.rishabh.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public Page<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page);
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id){
        return expenseService.getExpenseById(id) ;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public void deleteExpenseById(@PathVariable("id") Long id){
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@PathVariable("id") Long id,
                                        @RequestBody Expense e){
        return expenseService.updateExpense(id,e);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpenseByCategory(@RequestParam String category,Pageable page){
        return expenseService.readByCategory(category,page);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword,Pageable page){
        return expenseService.readByKeyword(keyword,page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesByDate(@RequestParam(name = "start" ,required = false) Date start, @RequestParam(name="end",required = false) Date end,Pageable page){
        return expenseService.readByDate(start,end,page);
    }
}
