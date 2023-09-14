package controllers;

import DAO.PlayerDAO;
import services.CalculationService;
import services.OngoingMatchesService;

import javax.servlet.http.HttpServlet;


public class BaseServlet extends HttpServlet {
    protected OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected CalculationService calculationService = new CalculationService();
    protected PlayerDAO playerDAO = new PlayerDAO();

}
