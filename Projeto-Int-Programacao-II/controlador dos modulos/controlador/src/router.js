const express = require('express');
const usuarioController = require('./controllers/usuarioController');
const usuarioMiddleware = require('./middleweres/usuarioMiddleware');

//const lojaController = require('./controllers/lojaController');

const router = express.Router();

const usuarioRouter = express.Router();
const ticktRounter = express.Router();
const cardapioRouter = express.Router();
const relatorioRouter = express.Router();

router.use('/usuario', usuarioRouter);
router.use('/tickt', ticktRounter);
router.use('/cardapio', cardapioRouter);
router.use('/relatorio', relatorioRouter);



//usuario
//usuarioRouter.get('/listar', usuarioController.getAll);
usuarioRouter.post('/login', usuarioMiddleware.validateBodyLogin, usuarioController.signIn);
//usuarioRouter.post('/cadastro', usuarioMiddleware.validateBodyCadastro, usuarioController.sigUp);
//usuarioRouter.get('/busca',usuarioMiddleware.verifyJTW,usuarioController.buscarUsuario);
//usuarioRouter.delete('/excluir',usuarioController.excluirUsuario);







module.exports = router;