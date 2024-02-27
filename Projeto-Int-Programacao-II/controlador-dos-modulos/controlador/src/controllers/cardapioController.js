const axios = require('axios');


const cadastrar_opcao = async (req, res) =>{
    const response =  await axios.post('http://localhost:3335/cardapio/cadastrarOpcao', req.body);
    return res.status(response.status).json(response.data);
};

const buscar_opcao = async (req, res) =>{
    const response =  await axios.post('http://localhost:3335/cardapio/buscarOpcao', req.body);
    return res.status(response.status).json(response.data);
};

const editar_opcao = async (req, res) =>{
    const response =  await axios.post('http://localhost:3335/cardapio/editarOpcao', req.body);
    return res.status(response.status).json(response.data);
};


const cadastrar_cardapio = async (req, res) =>{
    const response =  await axios.post('http://localhost:3335/cardapio/cadastrar', req.body);
    return res.status(response.status).json(response.data);
};


const buscar_cardapio = async (req, res) =>{
    const response =  await axios.post('http://localhost:3335/cardapio/buscar', req.body);
    return res.status(response.status).json(response.data);
};





module.exports = {
    cadastrar_opcao,
    buscar_opcao,
    editar_opcao,
    cadastrar_cardapio,
    buscar_cardapio
};