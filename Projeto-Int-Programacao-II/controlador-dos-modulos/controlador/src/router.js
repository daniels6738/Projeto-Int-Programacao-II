const express = require('express');
const usuarioController = require('./controllers/usuarioController');
const usuarioMiddleware = require('./middleweres/usuarioMiddleware');
const ticketController = require('./controllers/ticketController');
const relatorioController = require('./controllers/relatorioController');
const cardapioController = require('./controllers/cardapioController');

//const lojaController = require('./controllers/lojaController');

const router = express.Router();

const usuarioRouter = express.Router();
const ticketRounter = express.Router();
const cardapioRouter = express.Router();
const relatorioRouter = express.Router();

router.use('/usuario', usuarioRouter);
router.use('/ticket', ticketRounter);
router.use('/cardapio', cardapioRouter);
router.use('/relatorio', relatorioRouter);



//usuario
usuarioRouter.get('/listar', usuarioController.getAll);
usuarioRouter.post('/login', usuarioMiddleware.validateBodyLogin, usuarioController.signIn);
usuarioRouter.post('/cadastro', usuarioMiddleware.validateBodyCadastro, usuarioController.sigUp);
usuarioRouter.post('/busca',usuarioMiddleware.verifyJTW,usuarioController.buscarUsuario);
usuarioRouter.post('/editar',usuarioController.editUser);
usuarioRouter.post('/excluir',usuarioController.excluirUsuario);

//ticket
ticketRounter.post('/comprar',ticketController.comprar);
ticketRounter.post('/consumir',ticketController.consumir);
ticketRounter.get('/listar',ticketController.listar);


//relatorio
relatorioRouter.post('/vendidos', relatorioController.vendidos);
relatorioRouter.post('/consumidos', relatorioController.consumidos );


//cardapio
cardapioRouter.post('/cadastrarOpcao', cardapioController.cadastrar_opcao);
cardapioRouter.post('/buscarOpcao', cardapioController.buscar_opcao);
cardapioRouter.post('/editarOpcao', cardapioController.editar_opcao);
cardapioRouter.post('/cadastrar', cardapioController.cadastrar_cardapio);
cardapioRouter.post('/buscar', cardapioController.buscar_cardapio);



module.exports = router;