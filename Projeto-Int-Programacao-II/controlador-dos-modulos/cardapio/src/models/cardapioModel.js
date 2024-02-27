const connection = require('./connection');
require('dotenv').config();


const cadastrar_opcao = async (opcao1, opcao2, vegana, fast_grill, suco, sobremesa ) => {
    try {
        console.log("entrou 1");
        const query = 'INSERT INTO opcao_refeicao (opcao1, opcao2, vegana, fast_grill, suco, sobremesa) VALUES (?, ?, ?, ?, ?, ?)';
        console.log("entoru 2");
        const [result] = await connection.execute(query, [opcao1, opcao2, vegana, fast_grill, suco, sobremesa]);

        // Recuperar o ID gerado após a inserção
        console.log("chegou 1");
        const codigoOpcaoInserida = result.insertId;
        console.log("cheogu 2");

        return codigoOpcaoInserida;
    } catch (error) {
        console.error("Erro ao cadastrar opção de refeição:", error);
        throw error;
    }
};

const buscar_opcao = async (codigoOpcao) => {
    try {
        const query = 'SELECT * FROM opcao_refeicao WHERE codigo_opcao = ?';
        const [opcoes] = await connection.execute(query, [codigoOpcao]);
        return opcoes;
    } catch (error) {
        console.error("Erro ao buscar opção de refeição:", error);
        throw error;
    }
};

const editar_opcao = async (codigo, opcao1, opcao2, vegana, fast_grill, suco, sobremesa) => {
    try {
        // Consulta SQL para editar a opção de refeição
        const query = 'UPDATE opcao_refeicao SET opcao1 = ?, opcao2 = ?, vegana = ?, fast_grill = ?, suco = ?, sobremesa = ? WHERE codigo_opcao = ?';
        
        // Executar a consulta
        await connection.execute(query, [opcao1, opcao2, vegana, fast_grill, suco, sobremesa, codigo]);
        
        return {mensagem :'Opção de refeição editada com sucesso.'};
    } catch (error) {
        console.error("Erro ao editar opção de refeição:", error);
        throw error;
    }
};


const cadastrar_cardapio = async (dataInicio, tipo, opcaoSegunda, opcaoTerca, opcaoQuarta, opcaoQuinta, opcaoSexta) => {
    try {
        const query = 'INSERT INTO cardapio (data_inicio, tipo, opcao_segunda, opcao_terca, opcao_quarta, opcao_quinta, opcao_sexta) VALUES (?, ?, ?, ?, ?, ?, ?)';
        await connection.execute(query, [dataInicio, tipo, opcaoSegunda, opcaoTerca, opcaoQuarta, opcaoQuinta, opcaoSexta]);
        return {mensagem:"Cardápio cadastrado com sucesso."}
    } catch (error) {
        console.error("Erro ao cadastrar cardápio:", error);
        throw error;
    }
};


const buscar_cardapio = async (dataInicio, tipo) => {
    try {
        // Consulta SQL para obter o cardápio com base na data de início e tipo
        const query = 'SELECT * FROM cardapio WHERE data_inicio = ? AND tipo = ?';
        const [result] = await connection.execute(query, [dataInicio, tipo]);

        // Verificar se o cardápio foi encontrado
        if (result.length === 0) {
            return {mensagem:"Cardápio não encontrado."};
            
        }

        // Extrair os detalhes das opções de cada dia do cardápio
        const opcoes = {
            segunda: await buscar_opcao(result[0].opcao_segunda),
            terca: await buscar_opcao(result[0].opcao_terca),
            quarta: await buscar_opcao(result[0].opcao_quarta),
            quinta: await buscar_opcao(result[0].opcao_quinta),
            sexta: await buscar_opcao(result[0].opcao_sexta)
        };

        // Retornar o cardápio com as opções detalhadas
        return {
            data_inicio: result[0].data_inicio,
            tipo: result[0].tipo,
            opcoes: opcoes
        };
    } catch (error) {
        console.error("Erro ao buscar cardápio:", error);
        throw error;
    }
};



module.exports ={
    cadastrar_opcao,
    buscar_opcao,
    cadastrar_cardapio,
    buscar_cardapio,
    editar_opcao
}