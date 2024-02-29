const axios = require('axios');


const cadastrar_opcao = async (req, res) =>{
    const response =  await axios.post('http://localhost:3337/cardapio/cadastrar', req.body);
    return res.status(response.status).json(response.data);
};

const buscar_opcao = async (req, res) =>{
    const response =  await axios.post('http://localhost:3337/cardapio/buscar', req.body);
    return res.status(response.status).json(response.data);
};

const editar_opcao = async (req, res) =>{
    const response =  await axios.post('http://localhost:3337/cardapio/editar', req.body);
    return res.status(response.status).json(response.data);
};






module.exports = {
    cadastrar_opcao,
    buscar_opcao,
    editar_opcao
};