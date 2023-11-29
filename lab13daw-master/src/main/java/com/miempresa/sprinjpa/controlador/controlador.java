package com.miempresa.sprinjpa.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.sprinjpa.interfaceServicio.IEmpleadoServicio;
import com.miempresa.sprinjpa.interfaceServicio.ITareaServicio;
import com.miempresa.sprinjpa.modelo.Empleado;
import com.miempresa.sprinjpa.modelo.Tarea;

@Controller
@RequestMapping
public class controlador {
    
    @Autowired
    private IEmpleadoServicio servicio;

    @Autowired
    private ITareaServicio servicioTarea;

    @GetMapping("/listarEmpleados")
    public String listarEmpleados(Model model){
        List<Empleado> empleados = servicio.listar();
        model.addAttribute("empleados", empleados);
        return "empleados";
    }
    @GetMapping("/listarTareas")
    public String listarTareas(Model model){
        List<Tarea> tareas = servicioTarea.listar();
        model.addAttribute("tareas", tareas);
        return "tareas";
    } 

    @GetMapping("/agregarEmpleado")
    public String agregarEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "agregarEmpleado";
    }

    @GetMapping("/agregarTarea")
    public String agregarTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "agregarTarea";
    }

    @PostMapping("/guardarEmpleado")
    public String guardarEmpleado(Empleado p){
        servicio.guardar(p);
        return "redirect:/listarEmpleados";
    }

    @PostMapping("/guardarTarea")
    public String guardarTarea(Tarea p){
        servicioTarea.guardar(p);
        return "redirect:/listarTareas";
    }

    @GetMapping("/editarEmpleado/{id}")
    public String editarEmpleado(@PathVariable int id, RedirectAttributes atributos) {
        Optional<Empleado> empleado = servicio.listarId(id);
        atributos.addFlashAttribute("empleado", empleado);
        return "redirect:/mostrarEmpleado";
    }
    @GetMapping("/editarTarea/{id}")
    public String editarTarea(@PathVariable int id, RedirectAttributes atributos) {
        Optional<Tarea> tarea = servicioTarea.listarId(id);
        atributos.addFlashAttribute("tarea", tarea);
        return "redirect:/mostrarTarea";
    }

    @GetMapping("/mostrarEmpleado")
    public String mostrarEmpleado(@ModelAttribute("empleado") Empleado p, Model model) {
        model.addAttribute("empleado", p);
        return "agregarEmpleado";
    }

    @GetMapping("/mostrarTarea")
    public String mostrarTarea(@ModelAttribute("tarea") Tarea p, Model model) {
        model.addAttribute("tarea", p);
        return "agregarTarea";
    }

    @GetMapping("/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable int id) {
        try {
            servicio.borrar(id);
            return "redirect:/listarEmpleados";
        } catch (Exception e) {
            return "redirect:/error-eliminar-empleado";
        }
        
    }

    @GetMapping("/eliminarTarea/{id}")
    public String eliminarTarea(@PathVariable int id) {
        servicioTarea.borrar(id);
        return "redirect:/listarTareas";
    }

    @GetMapping("/buscarEmpleado")
    public String buscarEmpleado(Model model, @RequestParam String nombre) {
        List<Empleado> empleados = servicio.buscar(nombre);
        model.addAttribute("empleados",empleados);
        return "buscarEmpleado";
    }

    @GetMapping("/buscarTarea")
    public String buscarTarea(Model model, @RequestParam String descripcion) {
        List<Tarea> tareas = servicioTarea.buscar(descripcion);
        model.addAttribute("tareas",tareas);
        return "buscarTarea";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    //ERRORES
    @GetMapping("/error-agregar-empleado")
    public String handleAgregarEmpleadoError() {
        return "error-agregar-empleado";
    }

    @GetMapping("/error-agregar-tarea")
    public String handleAgregarTareaError() {
        return "error-agregar-tarea";
    }

    @GetMapping("/error-mostrar-empleado")
    public String handleMostrarEmpleadoError() {
        return "error-mostrar-empleado";
    }

    @GetMapping("/error-mostrar-tarea")
    public String handleMostrarTareaError() {
        return "error-mostrar-tarea";
    }

    @GetMapping("/error-eliminar-empleado")
    public String handleEliminarEmpleadoError() {
        return "error-eliminar-empleado";
    }

    @GetMapping("/error-eliminar-tarea")
    public String handleEliminarTareaError() {
        return "error-eliminar-tarea";
    }


}
