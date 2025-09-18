package com.example.wattbook.Service;

import com.example.wattbook.Dto.*;
import com.example.wattbook.Entity.Perfil;
import com.example.wattbook.Entity.Usuario;
import com.example.wattbook.Enums.Genero;
import com.example.wattbook.Repository.UsuarioRepository;
import com.example.wattbook.security.JWTService;
import com.example.wattbook.Enums.Rol;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.wattbook.Repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService, IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private PerfilService perfilService;
    private final PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private EmailService emailService;

    public Perfil obtenerPerfil(Long usuarioId) {
        return perfilRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado para el usuario con ID: " + usuarioId));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findTopByUsername(username).orElse(null);
    }

    public Usuario registrarUsuario(RegistroDTO dto){

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(dto.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        nuevoUsuario.setRol(Rol.USUARIO);



        Perfil perfil = new Perfil();
        perfil.setNombre(dto.getNombre());
        perfil.setApellidos(dto.getApellidos());
        perfil.setDescripcion(dto.getDescripcion());
        perfil.setGeneros(Genero.valueOf(dto.getGeneros()));
        perfil.setImagen(dto.getImagen());
        perfil.setEmail(dto.getEmail());

        String codigoVerificacion = generarCodigoVerificacion();
        nuevoUsuario.setCodigoVerificacion(codigoVerificacion);
        nuevoUsuario.setVerificado(false);


        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);


        perfil.setUsuario(usuarioGuardado);
        perfilService.guardarPerfil(perfil);




        emailService.enviarCodigoVerificacion(perfil.getEmail(), codigoVerificacion);


        return usuarioGuardado;
    }

    private String generarCodigoVerificacion() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }

    public String obtenerUsernamePorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(Usuario::getUsername).orElse(null);
    }


    public boolean comprobarVerificaddo(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario.isVerificado()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean verificarCodigo(VerificacionDTO verificacionDTO) {
        Usuario usuario = usuarioRepository.findByUsername(verificacionDTO.getUsername());
        if (usuario != null && usuario.getCodigoVerificacion().equals(verificacionDTO.getCodigoVerificacion())) {
            usuario.setVerificado(true);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }



    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


    public Usuario findById(Long usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        return usuarioOptional.orElse(null);
    }

    public ResponseEntity<RespuestaDTO> login(LoginDTO dto) {
        Optional<Usuario> usuarioOpcional = usuarioRepository.findTopByUsername(dto.getUsername());

        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();

            if (passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {

                String token = jwtService.generateToken(usuario);
                return ResponseEntity
                        .ok(RespuestaDTO.builder()
                                .estado(HttpStatus.OK.value())
                                .token(token)
                                .mensaje("Inicio de sesión exitoso")
                                .build());
            } else {
                throw new BadCredentialsException("Contraseña incorrecta");
            }
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }
    public String getUsernameById(Long authorId) {
        return usuarioRepository.findById(authorId)
                .map(Usuario::getUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public List<UsuarioDTO> getAllPerfiles() {
        return usuarioRepository.findAll().stream()
                .map(this::convertUsuarioToDTO)
                .collect(Collectors.toList());
    }

    private UsuarioDTO convertUsuarioToDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getRol(), usuario.getUsername(), usuario.getPassword());
    }



}
