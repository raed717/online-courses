package com.example.geekslabo.Services.Activity;

import com.example.geekslabo.Entities.AppUser;
import com.example.geekslabo.Entities.Claim;
import com.example.geekslabo.IServices.IServiceActivities.IClaimService;
import com.example.geekslabo.Repositories.ActivityRepo.ClaimRepository;
import com.example.geekslabo.Repositories.UserRepo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;


@Service
@AllArgsConstructor
public class ClaimService implements IClaimService {


    ClaimRepository repo;
    AppUserRepository repuser;

    @Override
    public List<Claim> GetallClaims() {
        return repo.findAll();
    }

    @Override
    public Claim AddClaim(Claim claim) {
        return repo.save(claim);
    }

    @Override
    public Claim findClaimById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
@Override
    public Claim updateClaim(Integer id, Claim claim) {

        Claim oldclaim = repo.findById(id).orElse(null);
    if (oldclaim == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found with id " + id);
    }

        oldclaim.setDescription(oldclaim.getDescription());
        oldclaim.setStatus(oldclaim.getStatus());
        return repo.save(oldclaim);
    }

 
    public void ajouterEtaffecterClaim(List<Claim> claim, Integer idUser) {
        repo.saveAll(claim);
        AppUser user = repuser.findById(idUser).orElse(null);

        for (Claim cl : claim) {
            cl.setUser(user);
        }
        repo.saveAll(claim);
    }
}
