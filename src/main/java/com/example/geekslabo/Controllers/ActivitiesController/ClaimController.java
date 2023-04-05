package com.example.geekslabo.Controllers.ActivitiesController;


import com.example.geekslabo.Entities.Claim;
import com.example.geekslabo.IServices.IServiceActivities.IClaimService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/Claims")
public class ClaimController {
    IClaimService serv;

    @PostMapping("/add-claim")
    public void ajouterReclamation(@RequestBody Claim reclamation) {
        serv.AddClaim(reclamation);
    }
    @GetMapping("/list-claims")
    public List<Claim> GetallClaims() {
        return serv.GetallClaims();
    }
    @PutMapping("/{id}")
    public Claim updateClaim(@PathVariable Integer id, @RequestBody Claim claim) {
        return serv.updateClaim(id, claim);
    }


    @PostMapping("/add-listeClaim/{idUser}")
    @ResponseBody
    public void ajouterEtaffecterClaim(@RequestBody  List<Claim> claim ,@PathVariable Integer idUser){
        serv.ajouterEtaffecterClaim(claim,idUser);

    }

}
