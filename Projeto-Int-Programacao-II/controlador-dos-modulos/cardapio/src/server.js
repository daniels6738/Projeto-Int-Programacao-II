const app = require('./app');
require('dotenv').config();

 const PORT = process.env.PORT  || 3335;

app.listen(process.env.PORT,() => console.log("servidor rodando  "+PORT));