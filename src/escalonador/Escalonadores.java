/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonador;

import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Escalonadores {
    ArrayList<Integer> progresso = new ArrayList<Integer>();
    int TE = 0;
    ArrayList[] fila; 
    int[] quantun;
    

    
    Escalonadores(){
        
    }
    
    public ArrayList<Processo> escalonar_fcfs(ArrayList<Processo> processos,int esc){
        int indice = 0;
        for(int i = processos.size()-1; i > 0 ;i--){//busca primeiro processo deste escalonador
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE){
                indice = i;
            }
        }
        
        for(int i = 0; i < processos.size();i++){
            if(processos.get(i).Uso == true && processos.get(i).Esc == esc){//verifica se ha processo deste escalonador que foi preemptado
                processos.get(i).CPU--;//se houver processo preemptado, ele ganha a cpu, conforme seria senao tivesse sido preemptado
                progresso.add(processos.get(i).ID);//guarda ID do processo que usou a cpu
                if(processos.get(i).CPU == 0){//se acabou cpu burst desse processo, remove da arraylist
                    remover_processo(i);//chama funçao para atualizar indice de processos posteriores a esse removido nas listas circulares
                    processos.remove(i);
                }
                else{
                    processos.get(i).Uso = true;//marce verdadeira para mostrar que foi preemptado
                }
                TE++;//aumenta um no tempo de execuçao
                return processos;
            }
        }
        for(int i = 0; i < processos.size();i++){//se nao havia processo preemptado, busca o primeiro processo desse escalonador
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE){
                if(processos.get(i).TC < processos.get(indice).TC && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;//busca processo com menor tempo de chegada
                }
                else if(processos.get(i).TC == processos.get(indice).TC && processos.get(i).CPU < processos.get(indice).CPU && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;//se tempo de chegada empatado, busca o com menor cpu burst
                }
                else if(processos.get(i).TC == processos.get(indice).TC && processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).ID < processos.get(indice).ID && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;//se empatado denovo busca pelo processo criado primeiro
                }
            } 
        }
        if(processos.get(indice).TC <= TE){//se houver mesmo um processo pronto para usar a cpu a condiçao é verdadeira
            processos.get(indice).CPU--;//diminui um cpu burst do processo
            progresso.add(processos.get(indice).ID);//adiciona o processo na lista para mostragem grafica
            if(processos.get(indice).CPU == 0){//se acabou cpu burst desse processo, remove da arraylist
                remover_processo(indice);//chama funçao para atualizar indice de processos posteriores a esse removido nas listas circulares
                processos.remove(indice);
            }
            else{
                processos.get(indice).Uso = true;//se nao acabou a cpu burst, o processo é preemptado para busca de escalonador com maior prioridade
            }
        }
        else{
            progresso.add(-1);//se ficou ocioso a cpu adiciona para mostragem grafica
        }
            
        TE++;//aumenta um tempo de execuçao
        return processos;
    }
    
    public ArrayList<Processo> escalonar_sjf(ArrayList<Processo> processos,int esc){
        int indice = 0;
        for(int i = processos.size()-1; i > 0 ;i--){
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE){
                indice = i;
            }
        }
        for(int i = 0; i < processos.size();i++){//busca processo que foi preemptado
            if(processos.get(i).Uso == true && processos.get(i).Esc == esc){
                processos.get(i).CPU--;
                progresso.add(processos.get(i).ID);
                if(processos.get(i).CPU == 0){
                    remover_processo(i);
                    processos.remove(i);
                }
                else{
                    processos.get(i).Uso = true;
                }
                TE++;
                return processos;
            }
        }
        for(int i = 0; i < processos.size();i++){//mesmo principio que o FCFS porem aqui busca menor cpu burst ao inves de menor TC
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE && processos.get(i).Esc == processos.get(indice).Esc){
                if(processos.get(i).CPU < processos.get(indice).CPU){
                    indice = i;
                }
                else if(processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).TC < processos.get(indice).TC && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;
                }
                else if(processos.get(i).TC == processos.get(indice).TC && processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).ID < processos.get(indice).ID && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;
                }
            } 
        }
        if(processos.get(indice).TC <= TE){//mesmo principio que o anterior
            processos.get(indice).CPU--;
            progresso.add(processos.get(indice).ID);
            if(processos.get(indice).CPU == 0){
                remover_processo(indice);
                processos.remove(indice);
            }
            else{
                processos.get(indice).Uso = true;
            }
        }
        else{
            progresso.add(-1);
        }
        TE++;
        return processos;
    }
    public ArrayList<Processo> escalonar_srtf(ArrayList<Processo> processos,int esc){
        int indice = 0;
        for(int i = processos.size()-1; i > 0 ;i--){//busca o primeiro processo
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE){
                indice = i;
            }
        }
        for(int i = 0; i < processos.size();i++){//mesmo principio que SJF porem com preempçao, entao nao busca processo que foi preemptado
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE && processos.get(i).Esc == processos.get(indice).Esc){
                if(processos.get(i).CPU < processos.get(indice).CPU){
                    indice = i;
                }
                else if(processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).TC < processos.get(indice).TC && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;
                }
                else if(processos.get(i).TC == processos.get(indice).TC && processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).ID < processos.get(indice).ID && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;
                }
            } 
        }
        if(processos.get(indice).TC <= TE){//mesmo principio que anteriores
            processos.get(indice).CPU--;
            progresso.add(processos.get(indice).ID);
            if(processos.get(indice).CPU == 0){
                remover_processo(indice);
                processos.remove(indice);
            }
        }
        else{
            progresso.add(-1);
        }
        TE++;
        return processos;
    }
    private void remover_processo(int indice){//funcao para ajustar indices de processos posteriores ao removido
        for(int i = 0;i < fila.length;i++){
            for(int j = 0;j < fila[i].size();j++){//diminui o indice de todos processos posteriores ao removido nas listas circulares
                if((Integer)fila[i].get(j) > indice){
                    fila[i].set(j, (Integer)fila[i].get(j)-1);
                }
            }
        }
    }
    
    private void atualizar_fila(ArrayList<Processo> processos,int esc,int ordem){
        int indice = 0;//funcao para verificar se chegou processos novos nas listas circulares
        for(int i = processos.size()-1; i >= 0 ;i--){//busca o primeiro processo que esta pronto pra usar a cpu e nao esta na fila
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE && processos.get(i).emFila == false){
                indice = i;
            }
        }
        int menor = indice;
        boolean aux = true;
        for(int i = indice; i < processos.size();i++){
            for(int j = indice; j < processos.size();j++){//busca processos que nao estao na fila circular
                if(processos.get(j).emFila == false && processos.get(i).emFila == false){
                    if(processos.get(j).Esc == esc && processos.get(i).Esc == esc && processos.get(j).TC <= TE  
                            && processos.get(j).CPU < processos.get(i).CPU){//busca pelo com menor cpu burst que nao esta na fila circular
                        menor = j;
                        aux = true;
                    }
                    else if(processos.get(j).Esc == esc && processos.get(i).Esc == esc && processos.get(j).TC <= TE  
                            && processos.get(j).CPU == processos.get(i).CPU 
                            && processos.get(j).TC < processos.get(i).TC){//caso de empate busca pelo menor tempo de chegada
                        menor = j;
                        aux = true;
                    }
                    else if(processos.get(j).Esc == esc && processos.get(i).Esc == esc && processos.get(j).TC <= TE  
                            && processos.get(j).CPU == processos.get(i).CPU 
                            && processos.get(j).TC == processos.get(i).TC
                            && processos.get(j).ID < processos.get(i).ID){//caso de empate denovo busca pelo processo criado primeiro
                        menor = j;
                        aux = true;
                    }
                }
            }
            if(aux == true && processos.get(menor).emFila == false && processos.get(menor).Esc == esc){
                fila[ordem].add(menor);//add processo na fila circular com menor cpu burst, se houver mais de um, add o com menor tc entre eles, se houver mais de um denovo, add o criado primeiro
                processos.get(menor).emFila = true;
            }
            aux = false;
            menor = indice;
        }
        for(int i = indice; i < processos.size();i++){//quando sobrar apenas um processo que nao esta na fila circular, add ele no final
            if(processos.get(i).Esc == esc && processos.get(i).emFila == false && processos.get(i).TC <= TE){
                fila[ordem].add(i);
                processos.get(i).emFila = true;
            }
        }
    }
    public ArrayList<Processo> escalonar_rr(ArrayList<Processo> processos,int esc,int ordem){
        atualizar_fila(processos,esc,ordem);//chama funçao de atualizar fila circular
        for(int i = 0; i < processos.size();i++){//busca por processo que foi preemptado
            if(processos.get(i).Uso == true && processos.get(i).Esc == esc){
                processos.get(i).CPU--;//mesmo principio que anteriores
                processos.get(i).quantun++;
                TE++;
                progresso.add(processos.get(i).ID);
                if(processos.get(i).CPU == 0){
                    remover_processo(i);
                    processos.remove(i);
                    fila[ordem].remove(0);//remove processo da fila
                }   
                else if(processos.get(i).quantun == quantun[ordem]){//se o processo ja usou a cpu pelo quantun determinado ele sai
                    processos.get(i).Uso = false;
                    processos.get(i).emFila = false;//remove processo da fila circular
                    fila[ordem].remove(0);
                    atualizar_fila(processos, esc, ordem);//chama funçao para inserir processo recem retirado no final da fila se nao chegou processos novos
                    processos.get(i).quantun = 0;//zera o quantun do processo que terminou seu tempo na cpu
                }
                
                else{
                    processos.get(i).Uso = true;//senao acabou cpu burst e nem seu tempo da cpu, marca como preemptado e ele ira retornar a cpu na proxima vez desse escalonador
                }
                
                return processos;
            }
        }
        if(fila[ordem].get(0) != null){//senao houver processo preemptado, pega o primeiro processo da fila circular pra usar a cpu
            processos.get((int) fila[ordem].get(0)).CPU--;
            processos.get((int) fila[ordem].get(0)).quantun++;//indica que usou mais um quantun dentro da cpu
            TE++;
            progresso.add(processos.get((int) fila[ordem].get(0)).ID);//mesmo principio que anteriores
            if(processos.get((int) fila[ordem].get(0)).CPU == 0){//se a cpu burst do precesso acabou essa condiçao é verdadeira
                for(int i = 0; i < fila[ordem].size();i++){
                    if((int) fila[ordem].get(i) > (int) fila[ordem].get(0)){
                        fila[ordem].set(i,(int) fila[ordem].get(i)-1);//diminui indices de processos posteriores ao removido
                    }
                }
                remover_processo((int) fila[ordem].get(0));//remove esse processo da fila
                processos.remove((int) fila[ordem].get(0));
                fila[ordem].remove(0);
            }
            else if(processos.get((int) fila[ordem].get(0)).quantun == quantun[ordem]){//se o processo ja ficou tempo que podia na cpu, ele sai
                processos.get((int) fila[ordem].get(0)).Uso = false;
                processos.get((int) fila[ordem].get(0)).emFila = false;
                processos.get((int) fila[ordem].get(0)).quantun = 0;
                fila[ordem].remove(0);//remove processo da fila
                atualizar_fila(processos, esc, ordem);//chama funçao para inserir processo no final da fila
            }
            else{
                processos.get((int) fila[ordem].get(0)).Uso = true;//senao ele marca com preemptado
            }
        }
        else{
            progresso.add(-1);
        }
        return processos;
    }
    public ArrayList<Processo> escalonar_PsP(ArrayList<Processo> processos,int esc){
        int indice = 0;
        for(int i = processos.size()-1; i > 0 ;i--){//mesmo principio que anteriores
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE){
                indice = i;
            }
        }
        for(int i = 0; i < processos.size();i++){//busca processo que foi preemptado
            if(processos.get(i).Uso == true && processos.get(i).Esc == esc){
                processos.get(i).CPU--;//mesmo principio que anteriores
                progresso.add(processos.get(i).ID);
                if(processos.get(i).CPU == 0){
                    remover_processo(i);
                    processos.remove(i);
                }
                else{
                    processos.get(i).Uso = true;
                }
                TE++;
                return processos;
            }
        }
        for(int i = 0; i < processos.size();i++){
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE && processos.get(i).Esc == processos.get(indice).Esc){
                if(processos.get(i).Priori < processos.get(indice).Priori){
                    indice = i;//busca processo com maior prioridade
                }
                if(processos.get(i).Priori == processos.get(indice).Priori && processos.get(i).CPU < processos.get(indice).CPU){
                    indice = i;//se mesma prioridade, busca pelo menor cpu burst
                }
                else if(processos.get(i).Priori == processos.get(indice).Priori && processos.get(i).CPU == processos.get(indice).CPU 
                        && processos.get(i).TC < processos.get(indice).TC && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;//se empatado denovo, busca pelo menor tc
                }
                else if(processos.get(i).Priori == processos.get(indice).Priori && processos.get(i).TC == processos.get(indice).TC 
                        && processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).ID < processos.get(indice).ID 
                        && processos.get(i).Esc == processos.get(indice).Esc){//se empatado denovo, busca pelo processo criado primeiro
                    indice = i;
                }
            } 
        }
        if(processos.get(indice).TC <= TE){//mesmo principio que anteriores
            processos.get(indice).CPU--;
            progresso.add(processos.get(indice).ID);
            if(processos.get(indice).CPU == 0){
                remover_processo(indice);
                processos.remove(indice);
            }
            else{
                processos.get(indice).Uso = true;
            }
        }
        else{
            progresso.add(-1);
        }
        TE++;
        return processos;
    }
    public ArrayList<Processo> escalonar_PcP(ArrayList<Processo> processos,int esc){
        int indice = 0;//mesmo principio que anteriores
        for(int i = processos.size()-1; i > 0 ;i--){
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE){
                indice = i;
            }
        }
        for(int i = 0; i < processos.size();i++){//unica diferente desse pro sem preempçao é que ele nao busca processos que foram preemptados
            if(processos.get(i).Esc == esc && processos.get(i).TC <= TE && processos.get(i).Esc == processos.get(indice).Esc){
                if(processos.get(i).Priori < processos.get(indice).Priori){
                    indice = i;
                }
                if(processos.get(i).Priori == processos.get(indice).Priori && processos.get(i).CPU < processos.get(indice).CPU){
                    indice = i;
                }
                else if(processos.get(i).Priori == processos.get(indice).Priori && processos.get(i).CPU == processos.get(indice).CPU 
                        && processos.get(i).TC < processos.get(indice).TC && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;
                }
                else if(processos.get(i).Priori == processos.get(indice).Priori && processos.get(i).TC == processos.get(indice).TC 
                        && processos.get(i).CPU == processos.get(indice).CPU && processos.get(i).ID < processos.get(indice).ID 
                        && processos.get(i).Esc == processos.get(indice).Esc){
                    indice = i;
                }
            } 
        }
        if(processos.get(indice).TC <= TE){//mesmo principio que anteriores
            processos.get(indice).CPU--;
            progresso.add(processos.get(indice).ID);
            if(processos.get(indice).CPU == 0){
                remover_processo(indice);
                processos.remove(indice);
            }
        }
        else{
            progresso.add(-1);
        }
        TE++;
        return processos;
    }
}
