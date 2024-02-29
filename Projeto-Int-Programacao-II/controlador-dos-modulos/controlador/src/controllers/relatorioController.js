const axios = require ('axios');

const vendidos = async (req,res) => {
    const response =  await axios.post('http://localhost:3333/relatorio/vendidos', req.body);
    return res.status(response.status).json(response.data);
};

const consumidos = async (req,res) => {
    const response =  await axios.post('http://localhost:3333/relatorio/consumidos', req.body);
    return res.status(response.status).json(response.data);
};
module.exports = {
    vendidos,
    consumidos
}