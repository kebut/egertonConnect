package com.groupwork.pharmacy.management.system.repositories.confirmationTokens;

import com.groupwork.pharmacy.management.system.model.confirmationTokens.UserConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserConfirmationTokenRepository extends JpaRepository<UserConfirmationToken,Long> {
    @Query(value = "SELECT u FROM UserConfirmationToken u WHERE u.confirmationToken = ?1 " )
    UserConfirmationToken findUserByConfirmationToken(String confirmationToken);
}
