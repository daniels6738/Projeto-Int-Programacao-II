const connection = require('./connection');
const auth = require('../utils/auth');
const bcrypt = require('bcrypt');
const { v4: uuidv4 } = require('uuid');

require('dotenv').config();


const getAll = async () => {
    const [user] = await connection.execute('SELECT * FROM usuario');
    return user;
};


const buscarUsuario = async (cpf) =>{
    
    var query = 'SELECT * FROM usuario WHERE cpf = ?';
    const [usuarios] = await connection.execute(query, [cpf]);
  
    if (usuarios.length > 0) {
        const usuario = usuarios[0];
        return usuario;
      } else {
        return null;
      }
}



const sigIn = async (usuario,res) => {
    const {cpf, senha} = usuario;

    const [usuarioExiste] = await connection.execute('SELECT * FROM usuario WHERE cpf = ?',[cpf]);

    if(usuarioExiste.length === 0){
        return {mensagem: "Usuário e/ou senha inválidos"};
    }


    //const senhaCorreta = await bcrypt.compare(senha, usuarioExiste[0].senha);
    if (usuarioExiste[0].senha != senha) {
        return res.status(401).json({ mensagem: 'Usuário e/ou senha inválidos' });
      }

   // var query = 'SELECT id, data_criacao, data_atualizacao, ultimo_login, token FROM usuario WHERE email = ?';

    //const [outputUsuario] = await connection.execute(query, [email]);

    //connection.execute('UPDATE usuario SET ultimo_login = NOW() WHERE id = ?',[outputUsuario[0].id]);

    var usuario = { ...usuarioExiste[0] };
    //var token = auth.gerarToken({  id:outputUsuario[0].id, email });
    //usuario.token = token;

    return {usuario, mensagem:"funcionario"};
   

    
};




const sigUp = async (usuario,res) => {
    const {nome,cpf,senha} = usuario;

    const [usuarioExiste] = await connection.execute('SELECT * FROM usuario WHERE cpf = ?',[cpf]);

    if(usuarioExiste.length > 0){
        return {mensagem: "cpf já existe"};
    }

   
   // const senhaHash = await auth.criptografarSenha(senha);
   // const id = uuidv4();
   // var token = auth.gerarToken({id,email});
    var query = 'INSERT INTO usuario ( nome, cpf, senha) VALUES (?, ?, ?)';
    
    const [novoUsuario] = await connection.execute(query,[nome,cpf, senha]);

    var query = 'SELECT nome, cpf, senha FROM usuario WHERE cpf = ?';
    const [outputUsuario] = await connection.execute(query, [cpf]);
    var usuario = { ...outputUsuario[0] };
    //usuario.token = token;
    return usuario;
    
        
    
};

module.exports ={
    getAll,
    sigUp,
    sigIn,
    buscarUsuario
}