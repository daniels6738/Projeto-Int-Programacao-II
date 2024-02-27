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
        
        //verifica se o usuario é aluno
        var query = 'SELECT * FROM estudante WHERE cpf = ?';
        const [estudante] = await connection.execute(query,[cpf]);

         //verifica se o usuario é funcionario
         var query = 'SELECT * FROM funcionario WHERE cpf = ?';
         const [funcionario] = await connection.execute(query,[cpf]);

         if(estudante.length >0){
            return {...usuario, tipo: "estudante", matricula: estudante[0].matricula};
         } else {
            return {...usuario, tipo: "funcionario", salario:funcionario[0].salario, data_admin:funcionario[0].data_admin};
         } 

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
    
    var query = 'SELECT * FROM estudantes WHERE cpf = ?';
    const [estudante] = await connection.execute(query,[cpf]);
    if(estudante.length > 0){
        return {usuario, tipo:"estudante"};
    }
    return {usuario, tipo:"funcionario"};
   

    
};




const sigUp = async (usuario) => {
    const {nome,cpf,data_nascimento, email, senha, matricula, salario, data_admin, tipo} = usuario;

    const [usuarioExiste] = await connection.execute('SELECT * FROM usuario WHERE cpf = ?',[cpf]);

    if(usuarioExiste.length > 0){
        return {mensagem: "cpf já existe"};
    }

   
   // const senhaHash = await auth.criptografarSenha(senha);
   // const id = uuidv4();
   // var token = auth.gerarToken({id,email});
    var query = 'INSERT INTO usuario ( nome, cpf, data_nascimento, email, senha, dia_cadastro) VALUES (?, ?, ?, ?, ?, now())';
  
    const [novoUsuario] = await connection.execute(query,[nome,cpf, data_nascimento, email, senha]);

    if(tipo == 'estudante'){
        var query = 'INSERT INTO estudante (cpf, matricula) VALUES (?, ?)';
        const [userAluno] = await connection.execute(query,[cpf,matricula]);
        var aluno = {...userAluno[0]};
        return aluno;
        
    } else{
        var query = 'INSERT INTO funcionario (cpf, salario, data_admin) VALUES (?, ?, ?)'
        const [userfuncionario] = await connection.execute(query,[cpf, salario, data_admin]);
        var funcionario = {...userfuncionario[0]};
        return funcionario;
    }        
    
};

const deleteUser = async (cpf) => {

  const user = buscarUsuario(cpf);
  if(user == null){
    return {erro:'usuario não encontrado'};
  } else{
    var query = 'DELETE FROM usuario WHERE cpf = ?';
    await connection.execute(query, [cpf]);
    return { mensagem: 'Usuário excluído com sucesso' };
  }

};

const ediUser = async (cpf,usuario) => {
    const {nome,novocpf,data_nascimento, email, senha, matricula, salario, data_admin} = usuario;
    const user = buscarUsuario(cpf);

    if(user == null){
        return {erro:'usuario não existe'};
    } else {
        var query = 'UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?, email = ?, senha = ? WHERE cpf = ?'
         await connection.execute(query,[nome,novocpf,data_nascimento,email,senha, cpf]);

         if(user.tipo == 'estudante'){
            var query = 'UPDATE estudante SET cpf = ?,matricula = ? WHERE cpf = ?';
            await connection.execute(query,[novocpf,matricula]);
            return {mensagem:'sucesso'};

         } else {
            var query = 'UPDATE funcionario SET cpf = ?, salaraio = ?, data_admin = ? WHERE cpf = ?'
            await connection.execute(query[novocpf,salario,data_admin]);
            return {mensagem:'sucesso'};
         }
    }
};

module.exports ={
    getAll,
    sigUp,
    sigIn,
    buscarUsuario,
    deleteUser,
    ediUser
}