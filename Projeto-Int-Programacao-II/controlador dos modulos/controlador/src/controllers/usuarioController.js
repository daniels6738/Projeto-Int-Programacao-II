//const usuarioModel = require('../models/usuarioModel');
const axios = require('axios');

const getAll = async (req,res) => {


const response = await axios.get('http://localhost:3333/usuario/listar');

    if (response.status == 200)
        return res.status(200).json(response.data);
    else{
        return res.status(401).json({mensagem:'erro ao listar usuarios'});
    }
};


const signIn = async (req, res) => {
    try {
        // Extrai as informações de autenticação do corpo da requisição
        const { cpf, senha } = req.body;

        // Faz uma requisição para a outra API para autenticar o usuário
        const response = await axios.post('http://localhost:3333/usuario/login', {
            senha,
            cpf
        });

        // Verifica se a autenticação foi bem-sucedida na outra API
        if (response.status === 200) {
            // Retorna os dados do usuário autenticado da outra API
            return res.status(200).json(response.data);
        } else {
            // Se a autenticação falhar na outra API, retorna um erro
            return res.status(401).json({ message: 'Falha na autenticação' });
        }
    } catch (error) {
        // Se ocorrer um erro durante a requisição para a outra API, retorna um erro
        console.error('Erro ao autenticar usuário:', error);
        return res.status(500).json({ message: 'Erro interno do servidor' });
    }
};


const sigUp = async (req,res) => {

    const {nome, cpf ,senha} =req.body;

    const response = await axios.post('http://localhost:3333/usuario/login', {
        nome,
        cpf,
        senha
    });
    
    if(response.status == 200){
        return res.status(200).json(response.data);
    } else{
        return res.status(401).json(response.data)
    }

};

const buscarUsuario = async (req,res) => {
    const {cpf}= req.body;
  const response = await axios.post('http://localhost:3333/usuario/buscar',{
    cpf
  });
    if(response.status == 200){
        return res.status(200).json(response.data);
    } else{
        return response.status(404).json(response.data);
    }
}

const excluirUsuario = async (req,res) => {
    const {cpf} = req.body;
    const response = await axios.post('http://localhost:3333/usuario/excluir',{
        cpf
    });
    if(response.status == 200){
        return res.status(200).json(response.data);
    } else{
        return response.status(404).json(response.data);
    }
}


module.exports = {
    getAll,
    sigUp,
    signIn,
    buscarUsuario,
    excluirUsuario
};