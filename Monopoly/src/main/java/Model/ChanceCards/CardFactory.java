package Model.ChanceCards;

import java.awt.*;

import Model.Game;

public class CardFactory {
    public static ChanceCard[] makeCards(){
        return new ChanceCard[]{
                new MoveToCard("Ryk frem til start", "Start", 0, true),
                new MoveToCard("Ryk frem til start", "Start", 0, true),

                new GetPaidCard("Modtag udbytte af Deres aktier - kr. 1.000", "Aktier", 1000, false),
                new GetPaidCard("De modtager Deres aktieudbytte. Modtag kr. 1.000 af banken", "Aktier", 1000, false),
                new GetPaidCard("Modtag udbytte af Deres aktier - kr. 1.000.", "Aktier", 1000, false),
                new GetPaidCard("Kommunen har eftergivet er kvartals skat. Hæv i banken kr. 3.000.", "Kvartals skat", 3000, false),
                new GetPaidCard("De havde en række med elleve rigtige i tipning. Modtag kr. 1.000.", "Tipning", 1000, false),
                new GetPaidCard("Deres præmieobligation er udtrukket. De modtager kr. 1.000 af banken.", "Præmieobligation", 1000, false),
                new GetPaidCard("Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken.", "Egen avl", 200, false),
                new GetPaidCard("De har solgt nogle gamle møbler på auktion. Modtag kr. 1.000 af banken", "Møbler", 1000, false),
                new GetPaidCard("De har vundet i Klasselotteriet. Modtag kr. 500.", "Klasselotteriet", 500, false),
                new GetPaidCard("Deres præmieobligation er udtrukket. De modtager kr. 1.000 af banken.", "Præmieobligation", 1000, false),
                new GetPaidCard("Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1.000.", "Gageforhøjelse", 1000, false),
                new GetPaidCard("De har vundet i Klasselotteriet. Modtag kr. 500.", "Klasselotteriet", 500, false),
                new GetPaidCard("De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. " +
                        "Modtag fra hver medspiller kr. 500.", "Samenskudsgilde", 500, true),
                new GetPaidCard("De skal holde familiefest og får et tilskud fra hver medspiller på kr. 500.", "Familefest", 500, true),
                new GetPaidCard("Det er Deres fødselsdag. Modtag af hver medspiller kr. 200. ", "Fødselsdag", 500, true),

                new PayBankCard("Betal kr. 3.000 for reparation af Deres Vogn", "Vogn", 3000),
                new PayBankCard("Betal kr. 200 for levering af 2 kasser øl.", "Levering", 200),
                new PayBankCard("De har modtaget Deres tandlægeregning. Betal kr. 2.000", "Tandlægeregning", 2000),
                new PayBankCard("De har fået en parkeringsbøde. Betal kr. 200 i bøde.", "Parkeringsbøde", 200),
                new PayBankCard("Betal Deres bilforsikring - kr. 1.000.", "Bilforsikring", 1000),
                new PayBankCard("Betal kr. 3.000 for reparation af Deres Vogn", "Vogn", 3000),
                new PayBankCard("De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.", "Told", 200),
                new PayBankCard("De har købt 4 nye dæk til Deres vogn. Betal kr. 1.000.", "Dæk", 1000),
                new PayBankCard("Betal for vognvask og smøring kr. 300.", "Vognvask", 300),
                new PayBankCard("De har kørt frem for FULDT STOP. Betal kr. 1.000 i bøde.", "Fuldt stop", 1000),

                // Kender ikke index for nærmeste færge.
                new MoveToCard("Ryk frem til Rådhuspladsen", "Rådhuspladsen", 39, true),
                new MoveToCard("Ryk frem til Grønningen. Hvis De passerer START, indkassér da kr. 4.000", "Grønningen", 24, true),
                new MoveToCard("Tag med SFL-færgen. Flyt brikken frem, og hvis De passerer START, indkassér da kr. 4.000", "SFL", 5, true),
                new MoveToCard("Tag med SFL-færgen. Flyt brikken frem, og hvis De passerer START, indkassér da kr. 4.000.", "Færge", 5, true),
                new MoveToCard("Ryk frem til Vimmelskaftet. Hvis De passerer START, indkassér da kr. 4.000", "Vimmelskaftet", 32, true),
                new MoveToCard("Ryk frem til Strandvejen. Hvis De passerer START, indkassér da kr. 4.000", "Strandvejen", 19, true),
                new MoveToCard("Ryk frem til Frederiksberg Allé. Hvis De passerer START, indkassér da kr. 4.000", "Frederiksberg Allé", 11, true),
                new MoveToCard("Ryk 3 felter frem", "3 felter frem", 3),
                new MoveToCard("Ryk 3 felter frem", "3 felter frem", 3),
                new MoveToCard("Ryk 3 felter frem", "3 felter tilbage", -3),

                // IKKE modtage 4000kr
                new MoveToCard("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4.000.", "Fængsel", true),
                new MoveToCard("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4.000.", "Fængsel", true),

                new OutOfJailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. \n" +
                        "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.", "Fængsel"),
                new OutOfJailCard("I anledning af kongens fødselsdag benådes De herved for fængsel. \n" +
                        "Dette kort kan opbevares, indtil De får brug for det, eller De kan sælge det.", "Fængsel"),

        };
    }
}
