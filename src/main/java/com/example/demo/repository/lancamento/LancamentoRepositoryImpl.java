package com.example.demo.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.demo.model.Lancamento;
import com.example.demo.model.Lancamento_;
import com.example.demo.repository.filter.LancamentoFilter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        // Restrições
        Predicate[] predicates = inserirRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);

        inserirRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, query.getResultList().size());
    }

    private void inserirRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);

    }
    // Desnecessário sendo que temos o total de registros do filtro dando um .size
    // na lista.
    /*
     * private Long total(LancamentoFilter lancamentoFilter) { CriteriaBuilder
     * builder = manager.getCriteriaBuilder(); CriteriaQuery<Long> criteria =
     * builder.createQuery(Long.class); Root<Lancamento> root =
     * criteria.from(Lancamento.class);
     * 
     * Predicate[] predicates = inserirRestricoes(lancamentoFilter, builder, root);
     * criteria.where(predicates);
     * 
     * criteria.select(builder.count(root)); return
     * manager.createQuery(criteria).getSingleResult(); }
     */

    private Predicate[] inserirRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
            Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (lancamentoFilter.getDescricao() != null) {
            predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
                    "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento),
                    lancamentoFilter.getDataVencimentoDe()));
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento),
                    lancamentoFilter.getDataVencimentoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
