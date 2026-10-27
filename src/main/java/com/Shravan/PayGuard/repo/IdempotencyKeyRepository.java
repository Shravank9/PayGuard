
package com.Shravan.PayGuard.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shravan.PayGuard.modal.IdempotencyKey;

public interface IdempotencyKeyRepository
        extends JpaRepository<IdempotencyKey, Long> {

    Optional<IdempotencyKey> findByKeyValue(String keyValue);

}
