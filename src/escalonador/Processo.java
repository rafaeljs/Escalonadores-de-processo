/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonador;

/**
 *
 * @author Rafael
 */
public class Processo {
    int ID; //id do processo
    int TC; //tempo de chegada do processo
    int CPU;//cpu burst do processo
    int Priori; //prioridade do processo
    int Esc; //referencia de qual escalonador esse processo pertence
    boolean Uso; //referencia se essse processo foi preemptado pelo escalonador de processos
    int Escalonador; // indice do escalonador correspondente da lista escalonadores 0 = fcfs, 1 = sjf ...
    int quantun; //indica quantos do quantun esse processo ja executou
    boolean emFila; //indica se esse processos esta na fila circular
    
    Processo(int id, int tc, int cpu, int priori, int esc,int esca){
        ID = id;
        TC = tc;
        CPU = cpu;
        Priori = priori;
        Esc = esc;
        Uso = false;
        Escalonador = esca;
        quantun = 0;
        emFila = false;
    }
}
