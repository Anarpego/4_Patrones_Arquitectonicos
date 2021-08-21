export class UI {
  agregarRegistro(registro) {
    const listadoRegistros = document.getElementById("lista-registros");
    const element = document.createElement("div");
    element.innerHTML = `
            <div class="card text-center mb-4">
                <div class="card-body card-wrapper">
                    <div class="contenido">
                      <span><strong>Nombre</strong>: ${registro.nombre} </span>
                      <span><strong>Vacuna</strong>: ${registro.vacuna} </span>
                      <span><strong>Primera Dosis</strong>: ${registro.dosis1.toLocaleDateString()}</span>
                      <span><strong>Segunda Dosis</strong>: <span style="color:#d90030">${registro.dosis2.toLocaleDateString()}</span></span>
                    </div>
                    <a href="#" class="btn btn-danger" name="delete">Borrar</a>
                </div>
            </div>
        `;
    listadoRegistros.appendChild(element);
  }

  resetForm() {
    document.getElementById("formulario").reset();
  }

  borrarRegistro(element) {
    if (element.name === "delete") {
      element.parentElement.parentElement.remove();
      this.showMessage("Registro Borrado Permanentemente", "success");
    }
  }

  showMessage(message, cssClass) {
    const div = document.createElement("div");
    div.className = `alert alert-${cssClass} mt-2`;
    div.appendChild(document.createTextNode(message));

    const container = document.querySelector(".container");
    const app = document.querySelector("#App");

    container.insertBefore(div, app);

    setTimeout(function () {
      document.querySelector(".alert").remove();
    }, 3000);
  }
}
