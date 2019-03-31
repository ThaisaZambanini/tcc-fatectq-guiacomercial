package com.fatec.tcc.controller;

/*@RestController
@RequestMapping(path = "api/login")
@CrossOrigin*/
public class LoginController {

	/*@Autowired
	private UsuarioRepository userRepository;

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAllUsers() {
		List<Usuario> lista = userRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsersById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		Usuario user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping(value = "/usuario/cadastro/", headers = "Content-Type=application/json")
	@ResponseBody
	public ResponseEntity<Usuario> newUsuario(@RequestBody @Validated Usuario usuario) {
		Usuario usuarioSalvo = userRepository.save(usuario);
		if (usuarioSalvo != null) {
			return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Usuario> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Usuario userDetails) throws ResourceNotFoundException {
		Usuario user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado :: " + userId));
		user.setEmail(userDetails.getEmail());
		user.setNome(userDetails.getSobrenome());
		user.setSobrenome(userDetails.getNome());
		final Usuario updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	*//**
	 * Delete user map.
	 *//*
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		Usuario user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}*/
}
