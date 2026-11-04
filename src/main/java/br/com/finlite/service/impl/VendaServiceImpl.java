package br.com.finlite.service.impl;

import br.com.finlite.model.Venda;
import br.com.finlite.repository.VendaRepository;
import br.com.finlite.service.VendaService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;

    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    @Override
    public Optional<Venda> buscarPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    @Override
    public Venda salvar(Venda venda) {
        return vendaRepository.save(venda);
    }

    @Override
    public void excluir(Integer id) {
        vendaRepository.deleteById(id);
    }
}