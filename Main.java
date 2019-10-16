import java.io.*;
import java.util.*;

public class Main{

static Scanner input = new Scanner(System.in).useDelimiter("\n");

static String nome[][] = new String[10][5];
//static String recuperar[][] = new String[10][5];
static String comunidades[][] = new String[10][3];
static String minhascomunidades[][] = new String[10][10];
static String mensagem[][][] = new String[10][10][999];
static int mensagemid[] = new int[10];
static int amizades[][] = new int[10][10];
static int contas, contasaux;
static int i = 0, kaux = 0,id,limite = 0, mensagemax = 1;

public static void main (String[] args)
{
int repet = 1;
int repetl = 1;
boolean logado = false;
    
    while(repet == 1){
        repetl = 1;
        
        System.out.println("1- Criar conta;\n2- Entrar");

        int botao = input.nextInt();
        
        if(botao == 1 ){
            
            System.out.print("Digite seu Login: ");

            for(int j = 0; j< 5; j++){
                nome[i][j] = input.next();
                if(j == 0){
                    while(verifical(nome[i][j]) == true){
                        System.out.println("Este login ja existe!\n Por favor coloque outro:");
                        nome[i][j] = input.next();
                    }
                }
                System.out.print("\n");
                
                if(j == 0)
                    System.out.print("Digite seu Senha: ");
                if(j == 1)
                    System.out.print("Digite seu usuário: ");
                if(j == 2)
                    System.out.print("Digite seu relacionamento: ");
                if(j == 3)
                    System.out.print("Digite sua idade: ");
            }
            i++;
            contas++;
            //System.out.println(contas);
            //checar();
        
        }
        else if(botao == 2){
            System.out.println("-------------------------");
            System.out.println("      --Entrar--");
            System.out.println("-------------------------");
            while (repetl == 1){
                System.out.print("Digite seu Login: \n");
                String login = input.next();
                boolean chave1 = verifical(login);
                
                if(chave1 != true){
                    System.out.println("Login não existe/incorreto\n1-Para tentar novamente\n 2-Caso queira voltar a tela inicial");
                    repetl = input.nextInt();
                }else{
                    id = id2(login);
                    //System.out.println(id);
                    System.out.print("Digite seu Senha: \n");
                    String senha = input.next();
                    boolean chave2 = verificas(senha);
                    
                    if(chave2 == true){
                        System.out.println("logado");
                        logado = true;
                        repetl = 2;
                    }else{
                        System.out.println("Senha incorreta\n1-Para tentar novamente\n 2-Caso queira voltar a tela inicial");
                        repetl = input.nextInt();
                        logado = false;
                        
                    }
                }
            }            
        }
        if(logado == true){
            
            int on = 1;
            int aux = 1;
            
            while(on == 1){
                System.out.println("---------Digite apenas numeros nas instruções!---------");
                System.out.println("1-Editar Perfil\n2-Ver perfil\n3- Para menu de amizades\n4- Menu de comunidades\n5- Enivar mensagem \n6- Mensagens recebidas\n7- Excluir conta\n >=8- Deslogar");
                
                aux = input.nextInt();
                
                if(aux == 1){
                    System.out.println("Digite um novo nome de Usuario: ");
                    nome[id][2] = input.next();
                    System.out.println("Digite uma nova condição de relacionamento: ");
                    nome[id][3] = input.next();
                    System.out.println("Digite sua idade: ");
                    nome[id][4] = input.next();
                    
                }else if(aux == 2){
                
                    System.out.println("Usuario: " +nome[id][2]);
                    System.out.println("relacionamento: " +nome[id][3]);
                    System.out.println("idade: " +nome[id][4]);
                    
                }else if(aux == 3){
                    
                    System.out.println("Deseja adicionar alguem?\n1- Para sim:\n2- para não:\n3- Ver solicitações de amizade\n4- Ver amigos");
                    
                    int amigaux;
                    
                    int amigo = input.nextInt();
                    
                    if(amigo == 1){
                        
                        System.out.println("Digite o usuario a quem você deseja adicionar:");
                        String usuario = input.next();
                        int destinatario = amizadesaux(usuario);
                        //System.out.println(destinatario);//testando
                        if(destinatario != -100){
                            System.out.println("Solicitação enviada!");
                            amizades[id][destinatario] = 1;
                        }else{
                            System.out.println("Usuario inexistente!");
                        }
                        
                    }else if(amigo == 3){
                        int solicitacao = verificaramigo();
                        if(solicitacao != 0){
                            System.out.println("Você tem "+solicitacao+" solicitação(ões)!");
                            System.out.println("1- Deseja ver o(s) solicitante(s)\n 2- Ignorar e voltar ao menu");
                            int solicitantes = input.nextInt();
                                if(solicitantes == 1){
                                    pedidosadc();
                                }
                        }else{
                            System.out.println("Você tem "+solicitacao+" solicitação(ões)!");
                        }
                    }else if(amigo == 4){
                        amigosadc();
                    }
                }else if(aux == 4){
                    System.out.println("1-Para criar uma comunidade\n2- Para entrar em uma comunadade ou adiministra-la\n3- Voltar ao tela inicial");
                    int comunidade = input.nextInt();
                    
                    if(comunidade == 1){
                        System.out.println("Digite um nome para sua comunidade(Não use números!):");
                        String nomecomunidade = input.next();
                        System.out.println("Digite uma curta descrição(Não use números!):");
                        String descricao = input.next();
                        System.out.println("Digite uma senha:");
                        String admaster = input.next();
                        
                        comunidades[id][0] = nomecomunidade;
                        comunidades[id][1] = descricao;
                        comunidades[id][2] = admaster;
                        contasaux++;
                    }else if(comunidade == 2){
                        int iaux = 0;
                        System.out.println("Comunidades existentes = "+contasaux);
                        while(iaux < contasaux){
                            System.out.println("-------------------------");
                            System.out.println("Nome da comunidade:");
                            System.out.println(comunidades[iaux][0]);
                            System.out.println("Descricao:");
                            System.out.println(comunidades[iaux][1]);
                            System.out.println("-------------------------");
                            iaux++;
                            System.out.println("1- Se deseja entrar na comunidade ou adiministra-la\n2 - Se desejar ver mais comunidades\n3 - Comunidades que você participa");
                            int membro = input.nextInt();
                            
                            if(membro == 1){
                                System.out.println("Digite o nome da comunidade que você deseja participar");
                                String str = input.next();
                                //System.out.println("Digite o seu nome de usuario");
                                //String admcon = input.next();
                                System.out.println("Caso queira administra-la digite a senha(Caso não queira tecle enter e seja membro):");
                                String adm = input.next();
                                boolean chave3 = verificac(str);
                                boolean chave4 = verificaadm(adm);
                                
                                if(chave3 == true){
                                    for(int jaux = 0; jaux < contasaux; jaux++)
                                        minhascomunidades[id][jaux] = str;

                                    System.out.println("Você entrou na comunidade");
                                }else{
                                    System.out.println("comunidade não existe!");
                                }
                                if(chave4 == true){
                                    for(int jaux = 0; jaux < contasaux; jaux++)
                                        minhascomunidades[id][jaux] = str;
                                    
                                    System.out.println("Mude a descrição:\n1- Sim\n2- Não");
                                    int mude = input.nextInt();
                                    
                                    if(mude == 1){
                                        System.out.println("Digite a nova descrição");
                                        String descri = input.next();
                                        comunidades[id][1] = descri;
                                    }
                                    //System.out.println("Gerenciar membros?\n1- Sim\n2- Não");
                                    //int gerenciar = input.next();
                                    
                                    /*if(gerenciar == 1){
                                        for
                                    }*/
                                    
                                }else{
                                    System.out.println("senha incorreta");
                                }
                        
                            }else if(membro == 3){
                                int laux = 0;
                                while(laux < contasaux){
                                    if(minhascomunidades[id][laux] != null){
                                            System.out.println("-------------------------");
                                            System.out.println(minhascomunidades[id][laux]);
                                            System.out.println("-------------------------");
                                            laux++;
                                    }else{
                                        laux = 10;
                                    }
                                    System.out.println(laux);
                                }
                            }
                            
                        }
                    }else{}
                        
                    
                    
                }else if(aux == 5){
                    int repetm = 1;
                    while(repetm == 1){
                        System.out.println("Digite o nome do usuario a quem você queira falar");
                        String receptor = input.next();
                        
                        int idreceptor = msn(receptor);
                        
                        if(idreceptor != -100){
                            System.out.println("Digite uma mensagem que você queira envia-lo:\n--Coloque seu nome de usuario caso queira ser identificado!--");
                            String zap = input.next();
                            
                            mensagem[id][idreceptor][limite] = zap;
                            limite++;
                            mensagemax++;
                            //[id]++;
                            
                        }else{
                            System.out.println("Usuário inexistente");
                        }
                        System.out.println("1- Se desejar enviar uma nova mensagem:\n2- Caso queira voltar ao menu:");
                        repetm = input.nextInt();
                    }
                    
                }else if(aux == 6){
                    System.out.println("Digite o nome do Usuário que você deseja ver a conversa");
                    String locutor = input.next();
                    int idlocutor = mensseger(locutor);
                    
                    if(idlocutor != -100){
                            //int mensagemax = mensagemid[idlocutor];
                            //System.out.println(idlocutor);
                            
                            for(int xaux = 0; xaux <= mensagemax; xaux++){
                                if(mensagem[idlocutor][id][xaux] != null){
                                    System.out.println("________________________________");
                                    System.out.println(mensagem[idlocutor][id][xaux]);
                                }else{
                                    System.out.println("________________________________");
                                    System.out.println("Eu- "+mensagem[id][idlocutor][xaux]);
                                }
                            }
                            
                        }else{
                            System.out.println("Usuário inexistente");
                        }
                
                }else if(aux == 7){
                    System.out.println("Deseja deletar a contar?\n1-Para SIM\n 2- NÃO");
                    int deletar = input.nextInt();
                    
                    if(deletar == 1){
                        System.out.println("Você tem certeza?\n1- SIM\b 2-NÃO");
                        int certeza = input.nextInt();
                        if(certeza == 1){
                            nome[id][0] = null;
                            nome[id][1] = null;
                            nome[id][2] = null;
                            nome[id][3] = null;
                            nome[id][4] = null;
                            
                            for(int del = 0; del < 10; del++){
                                amizades[id][del] = 0;
                                amizades[del][id] = 0;
                            }
                        }
                    }
                    on = 0;
                    i--;
                    contas--;
                
                }else{
                    on = 0;
                }
                
            }
        }
    }


}

    public static boolean verifical(String login){

        for(int k = 0; k < contas; k++){
            if(login.equals(nome[k][0])){
                return true;
            }
        }
        return false;
    }
    public static boolean verificas(String senha){

        for(int k = 0; k < contas; k++){
            if(senha.equals(nome[k][1])){
                //System.out.println("True");
                return true;
            }
        }
        return false;
    }
    public static boolean verificac(String str){

        for(int k = 0; k < contasaux; k++){
            if(str.equals(comunidades[k][0])){
                //System.out.println("True");
                return true;
            }
        }
        return false;
    }
    public static boolean verificaadm(String adm){

        for(int k = 0; k < contasaux; k++){
            if(adm.equals(comunidades[k][2])){
                //System.out.println("True");
                return true;
            }
        }
        return false;
    }
    public static int id2(String login){

        for(int kaux = 0; kaux < contas; kaux++){
            if(login.equals(nome[kaux][0])){
                return kaux;
            }
        }
        return kaux;
    }
    public static int amizadesaux(String usuario){
        for(int iaux = 0; iaux < contas; iaux++){
            if(usuario.equals(nome[iaux][2])){
                //System.out.println(iaux);
                return iaux;
            }
        }
        return -100;
    }
    public static int verificaramigo(){
        int contador = 0;
        for(int iaux = 0; iaux < contas; iaux++){
            if(amizades[iaux][id] != 0 && amizades[id][iaux] == 0){
                contador++;
            }
        }
        return contador;
    }
    public static void pedidosadc(){
        for(int iaux = 0; iaux < contas; iaux++){
            if(amizades[iaux][id] == 1){
                System.out.println(nome[iaux][id]);
                System.out.println("1- Para adicionar\n2- Para recusar");
                int contador = input.nextInt();
                if(contador == 1){
                    amizades[id][iaux] = 1;
                    System.out.println("Vocês agora são amigos!");
                    //System.out.println(amizades[iaux][id]);
                    //System.out.println(amizades[id][iaux]);
                }else if(contador == 2){
                    System.out.println("Recusada");
                    amizades[iaux][id] = 0;
                }
            }
        }
    }
    public static void amigosadc(){
        System.out.println("--Amigos--");
        for(int iaux = 0; iaux < contas; iaux++){
            if(amizades[iaux][id] == 1 && amizades[id][iaux] == 1){
                System.out.println(nome[iaux][2]);
            }
        }
    }
    public static int msn(String receptor){
        for(int iaux = 0; iaux < contas; iaux++){
            if(receptor.equals(nome[iaux][2])){
                //System.out.println(iaux);
                return iaux;
            }
        }
        return -100;
    }
    public static int mensseger(String locutor){
        for(int iaux = 0; iaux < contas; iaux++){
            if(locutor.equals(nome[iaux][2])){
                //System.out.println(iaux);
                return iaux;
            }
        }
        return -100;
    }
    //public static void mensagemrecebida
    /*public static int id3(int amizades){

        for(int kaux = 0; kaux < contas; kaux++){
            if(amizades.equals(nome[kaux][2])){
                return kaux;
            }
        }
        return kaux;
    }*/
    
    public static void checar(){
        int l;
        for(l = 0; l < 10; l++){
            System.out.println(nome[l][0]);
        }
    }
}
