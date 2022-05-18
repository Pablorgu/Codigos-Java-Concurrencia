package lago;

public class Lago {
	private volatile int nivel = 0;
	private Peterson r;
	private Peterson p;
	private Peterson pr;
	public Lago(int valorInicial) {
		nivel = valorInicial;
	}

	// f0IncDec, f0Inc
	public void incRio0() {
		r.preProt0();
		pr.preProt0();
		nivel++;
		pr.postProt0();
		r.postProt0();
	}

	// f0IncDec, f1Inc
	public void incRio1() {
		r.preProt1();
		pr.preProt1();
		nivel++;
		pr.postProt1();
		r.postProt1();
	}

	// f1IncDec, f0Dec
	public void decPresa0() {
		p.preProt0();
		pr.preProt0();
		nivel--;
		pr.postProt0();
		p.postProt0();
	}

	// f1IncDec, f1Dec
	public void decPresa1() {
		p.preProt1();
		pr.preProt1();
		nivel--;
		pr.postProt1();
		p.postProt1();
	}

	public int nivel() {
		return nivel;
	}
}
