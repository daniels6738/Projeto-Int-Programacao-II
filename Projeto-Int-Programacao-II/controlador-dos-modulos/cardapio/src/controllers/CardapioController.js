const cardapioModel = require('../models/cardapioModel');
const moment = require('moment');



const cadastrar_opcao = async (req, res) =>{
    const {opcao1, opcao2, vegana, fast_grill, suco, sobremesa} = req.body;
    
    const codigo_opcao = await  cardapioModel.cadastrar_opcao(opcao1, opcao2, vegana, fast_grill, suco, sobremesa);
    return res.status(200).json(codigo_opcao);
};

const buscar_opcao = async (req, res) =>{
    const {codigoOpcao} = req.body;
    
    const opcao_refeicao = await  cardapioModel.buscar_opcao(codigoOpcao);
    return res.status(200).json(opcao_refeicao);
};

const editar_opcao = async (req, res) =>{
    const {codigo, opcao1, opcao2, vegana, fast_grill, suco, sobremesa} = req.body;
    
    const result = await  cardapioModel.editar_opcao(codigo, opcao1, opcao2, vegana, fast_grill, suco, sobremesa);
    return res.status(200).json(result);
};


const cadastrar_cardapio = async (req, res) =>{
    const {data_inicio, tipo, opcao_segunda, opcao_terca, opcao_quarta, opcao_quinta, opcao_sexta} = req.body;
    //dataInicio = moment(dataInicio, 'DD/MM/YYYY').format('YYYY-MM-DD');
    const result = await  cardapioModel.cadastrar_cardapio(data_inicio, tipo, opcao_segunda, opcao_terca, opcao_quarta, opcao_quinta, opcao_sexta);
    return res.status(200).json(result);
};


const buscar_cardapio = async (req, res) =>{
    const {data_inicio, tipo} = req.body;
   // dataInicio = moment(dataInicio, 'DD/MM/YYYY').format('YYYY-MM-DD');
    const cardapio= await  cardapioModel.buscar_cardapio(data_inicio, tipo);
    return res.status(200).json(cardapio);
};





module.exports = {
    cadastrar_opcao,
    buscar_opcao,
    editar_opcao,
    cadastrar_cardapio,
    buscar_cardapio
};