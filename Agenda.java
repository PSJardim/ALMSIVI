import java.util.Scanner;

public class Agenda
{
     
    private Red_Black agenda;
    
    public Agenda()
    {
        agenda = new Red_Black();
    }

    public  void menu()
    {
        Scanner in = new Scanner(System.in);
        while (true) 
        {
            System.out.println("\n1.- Adicione eventos\n"
                    + "2.- Delete eventos\n"
                    + "3.- Procure uma data\n"
                    + "4.- Imprima a arvore\n"
                    + "5.- Delete a arvore\n"
                    + "6.- Sair ");
            int escolha = in.nextInt();
            
            switch(escolha)
            {
                case 1:
                System.out.println("Escreva a data no formato dd/mm/aaaa:");
                String data = in.nextLine();
                String aux = data;
                System.out.println("Escreva o evento:");
                data = in.nextLine();
                agenda.add(aux, data);
                break;
                case 2:
                imprimeAgenda();
                System.out.println("Escolha um evento para deletar ou pressione 0 para sair");
                String del = in.nextLine();
                if(del.equals("0"))break;
                agenda.delete(del);
                break;
                case 3:
                System.out.println("Escreva a data no formato dd/mm/aaaa:");
                String key = in.nextLine();
                agenda.get(key);
                break;
                case 4:
                imprimeAgenda();
                break;
                case 5:
                agenda.clear();
                break;
                case 6:
                return;
            }
            

        }
    } 

    public void imprimeAgenda()
    {
        for(String s: agenda.positionsPre())
        {
            System.out.println(s + "\n");
        }
    }

    
}