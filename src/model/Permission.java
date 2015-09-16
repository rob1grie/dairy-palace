/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rob
 * 
 * Boolean parameters indicate whether the user has the specified permission
 */
public class Permission {
	private int id;
	private User user;
	private boolean viewChange;
	private boolean reports;
	private boolean orders;
	private boolean foodExpense;
	private boolean utilities;

	public Permission(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isViewChange() {
		return viewChange;
	}

	public void setViewChange(boolean viewChange) {
		this.viewChange = viewChange;
	}

	public boolean isReports() {
		return reports;
	}

	public void setReports(boolean reports) {
		this.reports = reports;
	}

	public boolean isOrders() {
		return orders;
	}

	public void setOrders(boolean orders) {
		this.orders = orders;
	}

	public boolean isFoodExpense() {
		return foodExpense;
	}

	public void setFoodExpense(boolean foodExpense) {
		this.foodExpense = foodExpense;
	}

	public boolean isUtilities() {
		return utilities;
	}

	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	
	
}
