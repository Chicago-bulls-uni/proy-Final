package models;

import enums.MetodoPago;
import java.util.Map;
import java.util.TreeMap;

public class Billetera {

    private double saldo;
    private double credito;
    private double debito;
    private double transferenciaElectronica;
    private Map<Pieza, Double> transaccionesExitosas;

    public Billetera() {
        this.saldo = 0.0;
        this.credito = 0.0;
        this.debito = 0.0;
        this.transferenciaElectronica = 0.0;
        this.transaccionesExitosas = new TreeMap<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void agregarSaldo(double monto) {
        this.saldo += monto;
    }

    public void pagoPieza(double monto, MetodoPago metodoPago, Pieza pieza) {
        switch (metodoPago) {
            case TARJETA_CREDITO:
                credito += monto;
                break;
            case TARJETA_DEBITO:
                debito += monto;
                break;
            case TRANSFERENCIA_ELECTRONICA:
                transferenciaElectronica += monto;
                break;
            default:
                System.out.println("Método de pago no válido");
        }
    }

    public void finPagoPieza(double monto, MetodoPago metodoPago, Pieza pieza) {
        if (saldoSuficiente(monto, metodoPago)) {
            switch (metodoPago) {
                case TARJETA_CREDITO:
                    credito -= monto;
                    break;
                case TARJETA_DEBITO:
                    debito -= monto;
                    break;
                case TRANSFERENCIA_ELECTRONICA:
                    transferenciaElectronica -= monto;
                    break;
                default:
                    System.out.println("Método de pago no válido");
            }
            agregarTransaccionExitosa(monto, pieza);
        } else {
            System.out.println("Saldo insuficiente para realizar el pago.");
        }
    }

    public boolean saldoSuficiente(double monto, MetodoPago metodoPago) {
        switch (metodoPago) {
            case TARJETA_CREDITO:
                return credito >= monto;
            case TARJETA_DEBITO:
                return debito >= monto;
            case TRANSFERENCIA_ELECTRONICA:
                return transferenciaElectronica >= monto;
            default:
                return false;
        }
    }

    public void agregarTransaccionExitosa(double monto, Pieza pieza) {
        transaccionesExitosas.put(pieza, monto);
    }

    public void mostrarTransaccionesExitosas() {
        System.out.println("Transacciones exitosas:");
        for (Map.Entry<Pieza, Double> entry : transaccionesExitosas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

	public void retirarSaldo(double montoaretirar) {
		this.saldo -= montoaretirar;
    }
		
	
}