package br.cupom.repository;

import br.cupom.entity.CupomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<CupomEntity, Long> {
}