package com.example.demo.repository;

import com.example.demo.model.*;
import java.time.LocalDate;
import java.util.*;
public interface UserAccountRepository {
    Optional<UserAccount> findByEmail(String email);
}
