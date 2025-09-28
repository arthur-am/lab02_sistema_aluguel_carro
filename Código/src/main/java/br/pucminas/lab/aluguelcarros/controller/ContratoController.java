package br.pucminas.lab.aluguelcarros.controller;

import br.pucminas.lab.aluguelcarros.model.Agente;
import br.pucminas.lab.aluguelcarros.model.Contrato;
import br.pucminas.lab.aluguelcarros.model.PedidoAluguel;
import br.pucminas.lab.aluguelcarros.service.AgenteService;
import br.pucminas.lab.aluguelcarros.service.ContratoService;
import br.pucminas.lab.aluguelcarros.service.PedidoAluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired private ContratoService contratoService;
    @Autowired private PedidoAluguelService pedidoAluguelService;
    @Autowired private AgenteService agenteService;

    @GetMapping("/novo/{pedidoId}")
    public String novoContratoForm(@PathVariable Long pedidoId, Model model) {
        PedidoAluguel pedido = pedidoAluguelService.buscarPorId(pedidoId).orElseThrow();
        Contrato contrato = new Contrato();
        contrato.setPedidoAluguel(pedido);

        List<Agente> bancos = agenteService.buscarPorTipo(Agente.TipoAgente.BANCO);

        model.addAttribute("contrato", contrato);
        model.addAttribute("bancos", bancos);
        return "form-contrato";
    }

    @PostMapping("/salvar")
    public String salvarContrato(@ModelAttribute Contrato contrato, Principal principal) {
        contratoService.gerarContrato(contrato, principal.getName());
        return "redirect:/agente/dashboard";
    }
}
