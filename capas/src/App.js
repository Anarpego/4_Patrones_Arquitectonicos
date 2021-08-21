import { Registro } from "./registro.js";
import { UI } from "./UI.js";

// DOM Events
document
  .getElementById("formulario")
  .addEventListener("submit", function (e) {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value,
      vacuna = document.getElementById("vacuna").value,
      dosis1 = document.getElementById("dosis1").value;


    // Creacion de objetos
    const registro = new Registro(nombre, vacuna, dosis1);
    const ui = new UI();

    //validacion
    if (nombre === "" || vacuna === "" || dosis1 === "") {
      ui.showMessage("Por favor llene todos los campos del formulario", "danger");
    }else{
      ui.agregarRegistro(registro);
      ui.showMessage("Registro ingresado Exitosamente", "success");
      ui.resetForm();
    }

  });

document.getElementById("lista-registros").addEventListener("click", (e) => {
  const ui = new UI();
  ui.borrarRegistro(e.target);
  e.preventDefault();
});
