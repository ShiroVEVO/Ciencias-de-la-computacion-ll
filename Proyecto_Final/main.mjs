import { nodes, links, data } from "./logic/initData.mjs";
import { setMetabolicRates, paintNodes, PMVEcuation, setSource, spreadTemperature } from "./logic/functions.mjs";

setMetabolicRates();
paintNodes();

const elem = document.getElementById('3d-graph');
const info = document.getElementById('node_info');
info.hidden = true;
const forceGraph = ForceGraph3D()
  (elem)
  .graphData({
    nodes: nodes,
    links: links
  })
  .nodeLabel('name')
  .onNodeHover(node => elem.style.cursor = node ? 'pointer' : null)
  .onNodeClick(node => {
    // ---
    info.hidden = false;
    let wanted_M = null;

    for (let i = 0; i < nodes.length; i++) {
      nodes[i] == node ? wanted_M = nodes[i].M : "";
    }

    let PMV = PMVEcuation(node.M);

    info.innerHTML = `
    <h3> ${node.name} <h3/>
    <h4>Tasa metabolica: </h4><h> ${node.M} <br></h>
    <h4>Sensación termica: </h4><h> ${PMV.toFixed(2)} <br></h>
    <h4>Temperatura del Aire: </h4><h> ${data.TA} <br></h>
    <h4>Aislamiento de la ropa: </h4><h> ${data.LCL} <br></h>
    <h4>Factor de superficie de la ropa: </h4><h> ${data.FCL.toFixed(2)} <br></h>
    <h4>Temperatura de la superficie de la ropa: </h4><h> ${data.TCL} <br></h>
    <h4>Temperatura radiante media: </h4><h> ${data.TR} <br></h>
    <h4>Coeficiente de transmisión de calor por convección: </h4><h> ${data.HC} <br></h>
    <h4>Presión parcial del vapor de agua: </h4><h> ${data.PA.toFixed(2)} <br></h>
    <h4>Material de las paredes: </h4><h> ${data.wallsMaterial} <br></h>
    <h4>Conductividad térmica de las paredes: </h4><h> ${data.thermalConductivity} <br></h>
    <h4>Espesor de las paredes: </h4><h> ${data.wallThickness} <br></h>
    <h4>Dimensiones de las paredes: </h4><h> 10 x 3 <br><br></h>
    <div id="buttons-container"><button class="simulacion">Fuente de calor</button>
    <button class="simulacion">Fuente de frío</button><div>
    `;

    const buttonHot = document.querySelectorAll(".simulacion")[0];
    const buttonCold = document.querySelectorAll(".simulacion")[1];

    buttonHot.addEventListener('click', () => {
      setSource(node, 1);
      forceGraph.graphData({
        nodes: nodes,
        links: links
      });

      spreadTemperature(node.id, true, forceGraph.graphData);
    });

    buttonCold.addEventListener('click', () => {
      setSource(node, -1);
      forceGraph.graphData({
        nodes: nodes,
        links: links
      });

      spreadTemperature(node.id, true, forceGraph.graphData);
    });
  });
ForceGraph3D().showNavInfo([true]);
