import { data, nodes, links } from "./initData.mjs";
const { e, PA, TA, FCL, TCL, TR, HC } = data;
const metabolicRates = [29.7, 210.6, 129.5, 40.3];

const PMVEcuation = (M) => {
  return (0.303 * Math.pow(e, -0.036 * M) + 0.028) * (M - 3.05 * Math.pow(10, -3) * (5733 - 6.99 * M - PA) - 0.42 * (M - 58.15) - 1.7 * Math.pow(10, -5) * M * (5867 - PA) - 0.0014 * M * (34 - TA) - 3.96 * Math.pow(10, -8) * FCL * (Math.pow((TCL + 273), 4) - Math.pow((TR + 273), 4)) - FCL * HC * (TCL - TA));
};

const heatTransfer = (wallArea, T1, T2) => {
  return (data.thermalConductivity * wallArea * (T1 - T2)) / (data.wallThickness * 20000);
};

const setMetabolicRates = () => {
  nodes.forEach((node, index) => {
    if (index % 4 === 0) metabolicRates.push(metabolicRates.shift());

    if (!node.M) node.M = metabolicRates[index % 4];
  });
};

const paintNodes = (isSource) => {
  nodes.forEach(node => {
    const PMV = isSource ? node.PMV : PMVEcuation(node.M);
    node.PMV = PMV;

    switch (true) {
      case PMV <= -2.5: node.color = 'white'; break;
      case PMV > -2.5 && PMV <= -1.5: node.color = 'blue'; break;
      case PMV > -1.5 && PMV <= -0.5: node.color = '#42F5EF'; break;
      case PMV > -0.5 && PMV <= 0.5: node.color = 'green'; break;
      case PMV > 0.5 && PMV <= 1.5: node.color = 'yellow'; break;
      case PMV > 1.5 && PMV <= 2.5: node.color = 'orange'; break;
      case PMV > 2.5: node.color = 'red'; break;
    }
  });
};

const setSource = (node, increase) => {
  node.PMV += increase;
  paintNodes(true);
};

const spreadTemperature = (id, first, graphData) => {
  let totalheat = 0, contInterval = 0;
  const node = nodes.find(item => item.id === id);

  let interval = setInterval(() => {
    contInterval++;

    node.edges.forEach(idLink => {
      const targetNode = nodes.find(item => item.id === idLink);
      const wall = links.find(link => link.source.id === id && link.target.id === idLink || link.source.id === idLink && link.target.id === id);
      const heat = heatTransfer(wall.wallArea, node.PMV, targetNode.PMV);

      targetNode.PMV += heat;
      node.PMV -= heat;
    });

    paintNodes(true);

    graphData({
      nodes: nodes,
      links: links
    });

    if (contInterval === 5) clearInterval(interval);

  }, 1000);

  if (first) {
    node.edges.forEach(idLink => {
      spreadTemperature(idLink, false, graphData);

      for (let i = 1; i < 5; i++) {
        if ((idLink - i * 6) > 0) spreadTemperature(idLink - i * 6, false, graphData);
        if ((idLink + i * 6) <= 30) spreadTemperature(idLink + i * 6, false, graphData);
      }
    });
  }
};

export {
  setMetabolicRates,
  paintNodes,
  PMVEcuation,
  heatTransfer,
  spreadTemperature,
  setSource
};
