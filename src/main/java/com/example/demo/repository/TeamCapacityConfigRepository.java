// Continue with remaining repository files...

// File: src/main/java/com/example/demo/repository/TeamCapacityConfigRepository.java
package com.example.demo.repository;
import com.example.demo.model.TeamCapacityConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamCapacityConfigRepository extends JpaRepository<TeamCapacityConfig, Long> {
    Optional<TeamCapacityConfig> findByTeamName(String teamName);
}
