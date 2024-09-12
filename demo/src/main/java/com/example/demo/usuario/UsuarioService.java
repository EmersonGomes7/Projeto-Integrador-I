package com.example.demo.usuario;

import com.example.demo.frequencia.FrequenciaRepository;
import com.example.demo.producao.ProducaoRepository;
import com.example.demo.publicacao.PublicacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        if (repository.existsById(id)) {
            return repository.getReferenceById(id);
        }
        return null;
    }

    public void atualizarInformacoes(Usuario usuario, DTODadosUsuario dados) {
        if (dados.nome() != null){
            usuario.setNome_usuario(dados.nome());
        }
        if (dados.tipo_usuario() != null){
            usuario.setTipo_usuario(dados.tipo_usuario());
        }
    }

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /* TODO: VER SE ISSO É USUAL
    @Transactional
    public void deleteProducoesPublicacoesFrequencias(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        // Deleta as produções associadas ao usuário
        producaoRepository.deleteByIdUsuarioCriador(usuario);

        // Deleta as publicações associadas ao usuário
        publicacaoRepository.deleteByIdUsuarioCriador(usuario);

        // Deleta as frequências associadas ao usuário
        frequenciaRepository.deleteByIdUsuarioProf(usuario);
    }
    */
}
