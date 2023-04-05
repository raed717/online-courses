package com.example.geekslabo.IServices.IServiceActivities;


import com.example.geekslabo.Entities.Claim;

import java.util.List;

public interface IClaimService {

    List<Claim> GetallClaims();
    public Claim AddClaim(Claim claim);
    void delete(Integer id);
    public Claim findClaimById(Integer id)  ;
    public Claim updateClaim(Integer id, Claim claim);
    public void ajouterEtaffecterClaim(List<Claim> claim, Integer idUser);

    }
