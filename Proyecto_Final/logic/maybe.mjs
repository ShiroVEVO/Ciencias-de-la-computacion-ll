// Wall
class Edge {
  constructor(initNode, finaleNode, resistence) {
    this.initNode = initNode;
    this.finaleNode = finaleNode;
    this.resistence = resistence
  }
}

// Room
class Node {
  constructor(id, name, group, x, y, z, objectsNum, peopleNum, initTemp, humidity) {
    this.id = id;
    this.name = name;
    this.group = group;
    this.x = x;
    this.y = y;
    this.z = z;

    this.objectsNum = objectsNum;
    this.peopleNum = peopleNum;
    this.initTemp = initTemp;
    this.humidity = humidity;
  }
}

const crearNodos = () => {
  let nodos = [];
  auxZ = [10, 20, 0, 20, 10]

  // Habitaciones
  for (let i = 1; i < 6; i++) {
    for (let j = 1; j < 6; j++) {
      nodos.push({
        name: "Habitación " + i + "0" + j,
        group: 1,
        id: j + (i - 1) * 6,
        x: (j - 1) * 10,
        y: (i - 1) * 10,
        z: auxZ[j - 1]
      });
    }
  }

  // Pasillos
  for (let i = 1; i < 6; i++) {
    nodos.push({
      name: "Pasillo " + i,
      group: 2,
      id: i * 6,
      x: 20,
      y: (i - 1) * 10,
      z: 10
    });
  }

  return nodos;
}

const crearLinks = () => {
  let links = [];

  // Habitación -> Pasillo
  for (let i = 1; i < 6; i++) {
    for (let j = 1; j < 6; j++) {
      links.push({
        "source": (i - 1) * 6 + j,
        "target": i * 6,
        "value": 0
      });
    }
  }

  // Habitación -> Habitación
  for (let i = 1; i < 5; i++) {
    for (let j = 1; j < 6; j++) {
      links.push({
        "source": (i - 1) * 6 + j,
        "target": i * 6 + j,
        "value": 0
      });
    }
  }

  // Pasillo -> pasillo
  for (let i = 1; i < 5; i++) {
    links.push({
      "source": i * 6,
      "target": (i + 1) * 6,
      "value": 0
    });
  }

  return links;
}