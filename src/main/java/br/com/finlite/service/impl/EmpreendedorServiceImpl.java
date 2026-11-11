package br.com.finlite.service.impl;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.repository.EmpreendedorRepository;
import br.com.finlite.service.EmpreendedorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpreendedorServiceImpl implements EmpreendedorService {

    private final EmpreendedorRepository empreendedorRepository;

    public EmpreendedorServiceImpl(
            EmpreendedorRepository empreendedorRepository) {
        this.empreendedorRepository = empreendedorRepository;
    }

    @Override
    @Transactional
    public Empreendedor salvar(Empreendedor empreendedor) {

        if (empreendedor.getNomeEmpreendedor() == null ||
                empreendedor.getNomeEmpreendedor().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do empreendedor é obrigatório."
            );
        }

        String cpf = empreendedor.getCpfEmpreendedor();

        if (cpf != null && !cpf.isBlank()) {

            cpf = cpf.replaceAll("\\D", "");

            if (!cpf.matches("\\d{11}")) {
                throw new IllegalArgumentException(
                        "O CPF deve possuir 11 números."
                );
            }

            empreendedor.setCpfEmpreendedor(cpf);
        }

        return empreendedorRepository.save(empreendedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empreendedor> listarTodos() {
        return empreendedorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empreendedor> buscarPorId(Integer id) {
        return empreendedorRepository.findById(id);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {

        if (!empreendedorRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Empreendedor não encontrado."
            );
        }

        empreendedorRepository.deleteById(id);
    }
}