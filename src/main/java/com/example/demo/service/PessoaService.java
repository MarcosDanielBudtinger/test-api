package com.example.demo.service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Pessoa atualizar(Long id, Pessoa pessoa){
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(id);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long id, Boolean ativo){
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(id);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    private Pessoa buscarPessoaPeloCodigo(Long id) {
        Pessoa pessoaSalva = this.pessoaRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return pessoaSalva;
    }
}
