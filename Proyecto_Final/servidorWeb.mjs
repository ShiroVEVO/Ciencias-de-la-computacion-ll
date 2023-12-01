import express from 'express';
import path from 'path';
import { fileURLToPath } from 'url';
import open from 'open';

const app = express();

const __dominio = fileURLToPath(import.meta.url);
const __nombreDirectorio = path.dirname(__dominio);

// Configuración para servir archivos con extensión ".mjs"
app.get('*.mjs', (req, res, next) => {
  res.header('Content-Type', 'application/javascript');
  next();
});

// Ruta para servir archivos estáticos (ajusta la ruta según tu estructura de archivos)
app.use(express.static(__nombreDirectorio));

// Puerto en el que escucha el servidor
const puerto = 8080;

// Iniciar el servidor
app.listen(puerto, () => {
  console.log(`Servidor escuchando en el puerto ${puerto}`);
  open(`http://localhost:${puerto}/View/index.html`);
});
