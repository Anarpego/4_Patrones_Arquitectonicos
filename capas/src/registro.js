// Product Constructor
export class Registro {
    constructor(nombre, vacuna, dosis1) {
        this.nombre = nombre;
        this.vacuna = vacuna;
        this.dosis1 = new Date(dosis1);
        this.dosis2 = this.programarDosis2();
    }

    programarDosis2(){
        console.log(this.dosis1);
        const dosis2 = new Date(this.dosis1)
        dosis2.setDate(dosis2.getDate() + 28)

        return dosis2
    }
}