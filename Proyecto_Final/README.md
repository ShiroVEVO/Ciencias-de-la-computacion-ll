# Proyecto-Final-Ciencias-De-La-Computación-ll

Ingeniería de Sistemas - Universidad Distrital Francisco José de Caldas  

**Asignatura:** Ciencias de la Computación II

**Docente:** Santiago Salazar

---

## Integrantes
- Amir Zoleyt Vanegas Cárdenas - 20211020015
- Juan David Mendoza Vargas - 20211020061
- Franco José Guzman Orjuela - 20211020155
- Adrián Stiven Olmos Ardila - 20181020039

---

## Importante
El presente proyecto se basa en la teoría de Povl Ole Fanger, donde se calcula el "voto medio estimado (PMV)", teniendo en cuenta las siguientes variables:

TA -> Temperatura del aire  
LCL -> Aislamiento de la ropa (0 a 1)  
TCL -> Temperatura de la superficie de la ropa  
TR -> Temperatura radiante media  
HC -> Coeficiente de transmición de calor por convección  
PA -> Presión parcial del vapor de agua  
FCL -> Factor de superficie de la ropa  
M -> Tasa metabólica  
Entre otras...  

Y con el resultado que nos entrega el PMV, se puede estimar la sensación térmica de las personas, a través del siguiente criterio:

Rango               |  Sensación Térmica

PMV > 2.5           |  Muy caluroso  
1.5 < PMV <= 2.5    |  Caluroso  
0.5 < PMV <= 1.5    |  Ligeramente caluroso  
-0.5 < PMV <= 0.5   |  Neutro  
-1.5 < PMV <= -0.5  |  Ligeramente frío  
-2.5 < PMV <= -1.5  |  Frío  
PMV <= -2.5         |  Muy frío  

Cabe aclarar que se hicieron algunos ajustes para disminuir un poco la complejidad de los cálculos.

A continuación, los valores de M para los cuales el PMV es 3, 2, 1, 0, -1, -2, -3 aproximadamente.

M -> PMV : Sensación térmica -> color => actividad

286.3 -> 2.9995 : Muy caluroso          -> rojo       => Extraordinario (cambio de clima, fuejo)  
210.6 -> 1.9996 : Caluroso              -> naranja    => Trabajo intenso con objetos y herramientas  
129.5 -> 0.9995 : Ligeramente caluroso  -> amarillo   => Ejercicio, baile, caminar rápido  
61.5 -> -0.0005 : Neutro                -> verde      => Clavar clavos, lijar, conducir  
40.3 -> -1.0009 : Ligeramente frío      -> aguamarina => Escribir, teclear, dibujar, coser  
29.7 -> -2.0011 : Frío                  -> azul       => Descansar, sentarse, estar cómodo  
22.6 -> -3.0092 : Muy Frío              -> blanco     => Estraordinario (cambio de clima, lluvia)  

min: 19 -> -3.664 : Lo más caluroso  
max: 325 -> 3.515 : Lo más frío  
