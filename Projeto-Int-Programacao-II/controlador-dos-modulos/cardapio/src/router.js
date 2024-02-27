const express = require('express');
const cardapioController = require('./controllers/CardapioController');



const router = express.Router();
const cardapioRouter = express.Router();
router.use('/cardapio', cardapioRouter);




//cardapio
cardapioRouter.post('/cadastrarOpcao', cardapioController.cadastrar_opcao);
cardapioRouter.post('/buscarOpcao', cardapioController.buscar_opcao);
cardapioRouter.post('/editarOpcao', cardapioController.editar_opcao);
cardapioRouter.post('/cadastrar', cardapioController.cadastrar_cardapio);
cardapioRouter.post('/buscar', cardapioController.buscar_cardapio);





module.exports = router;