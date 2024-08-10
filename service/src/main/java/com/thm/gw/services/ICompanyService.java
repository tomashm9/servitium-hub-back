package com.thm.gw.services;

import com.thm.gw.dtos.company.CompanyDTO;
import com.thm.gw.dtos.company.PagedCompaniesDTO;
import com.thm.gw.dtos.companylocation.CompanyLocationDTO;
import com.thm.gw.forms.company.CompanyForm;

import java.util.List;
import java.util.Map;

public interface ICompanyService {

    /**
     * Retrieves a list of companies based on provided filters.
     * @param filters Map containing filter key-value pairs.
     * @return a List of CompanyDTO objects that match the filters.
     */
    PagedCompaniesDTO getCompaniesByFilters(Map<String, String> filters, int page);

    /**
     * Retrieves a list of all companies.
     * @return a list of {@link CompanyDTO} representing all companies.
     */
    List<CompanyDTO> getAll();

    /**
     * Retrieves a company by its unique identifier.
     * @param id the identifier of the company to search for.
     * @return the {@link CompanyDTO} corresponding to the provided identifier, or null if not found.
     */
    CompanyDTO getCompanyById(Long id);

    /**
     * Retrieves a list of locations for a specific company.
     * @param companyId The ID of the company.
     * @return List of locations for the company.
     */
    List<CompanyLocationDTO> getCompanyLocations(Long companyId);

    /**
     * Creates a new company in the system.
     * @param form the {@link CompanyForm} containing the details of the company to add.
     * @return the newly created {@link CompanyDTO}.
     */
    CompanyDTO addCompany(CompanyForm form);

    /**
     * Updates an existing company.
     * @param id   the identifier of the company to update.
     * @param form the {@link CompanyForm} containing the new information for the company.
     * @return the updated {@link CompanyDTO}, or null if the company does not exist.
     */
    CompanyDTO updateCompany(Long id, CompanyForm form);

    /**
     * Deletes a company by its identifier.
     * @param id the identifier of the company to delete.
     * @return the deleted {@link CompanyDTO}, or null if the company does not exist.
     */
    CompanyDTO deleteCompany(Long id);

    /**
     * Adds a company as an admin.
     * @param userId the id of the user.
     * @param companyForm the company request.
     * @return the company response.
     */
    CompanyDTO addCompanyAsAdmin(Long userId, CompanyForm companyForm);

    /**
     * Updates a company as an admin.
     * @param id the id of the company.
     * @param companyForm the company request.
     * @return the company response.
     */
    CompanyDTO updateCompanyAsAdmin(Long id, CompanyForm companyForm);

    /**
     * Deletes a company as an admin.
     * @param id the id of the company.
     * @return the company response.
     */
    CompanyDTO deleteCompanyAsAdmin(Long id);

    /**
     * Toggles the active status of a company.
     * @param id       the identifier of the company.
     * @param isActive the new active status.
     * @return the updated {@link CompanyDTO}.
     */
    CompanyDTO triggerActive(Long id, boolean isActive);
}
