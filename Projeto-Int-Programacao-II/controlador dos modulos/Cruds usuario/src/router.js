const express = require('express');
const usuarioController = require('./controllers/usuarioController');
const usuarioMiddleware = require('./middleweres/usuarioMiddleware');

const lojaController = require('./controllers/lojaController');

const router = express.Router();

const usuarioRouter = express.Router();
router.use('/usuario', usuarioRouter);




//usuario
usuarioRouter.get('/listar', usuarioController.getAll);
usuarioRouter.post('/login', usuarioMiddleware.validateBodyLogin, usuarioController.sigIn);
usuarioRouter.post('/cadastro', usuarioMiddleware.validateBodyCadastro, usuarioController.sigUp);
usuarioRouter.get('/busca',usuarioMiddleware.verifyJTW,usuarioController.buscarUsuario);






module.exports = router;