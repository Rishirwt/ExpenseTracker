package com.rishabh.expensetracker.repository;

import com.rishabh.expensetracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    Page<Expense> findByUserIdAndCategory(Long usedId ,String category, Pageable page);

    Page<Expense> findByUserIdAndNameContaining(Long userId ,String keyword,Pageable page);

    Page<Expense> findByUserIdAndDateBetween(Long userId ,Date start,Date end,Pageable page);

    Page<Expense> findByUserId(Long userId,Pageable page);

    Optional<Expense> findByUserIdAndId(Long userId , Long id );
}
