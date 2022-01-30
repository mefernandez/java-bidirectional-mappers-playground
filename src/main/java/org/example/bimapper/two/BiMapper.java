package org.example.bimapper.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BiMapper <S, T> {

	private static class SourceTargetFunctions<P> {
		Function<P, P> fs;
		Function<P, P> ft;
		public SourceTargetFunctions(Function<P, P> fs, Function<P, P> ft) {
			this.fs = fs;
			this.ft = ft;
		}
	}
	
	private List<SourceTargetFunctions> functions = new ArrayList<>();

	public <P> void add(Function<P, P> fs, Function<P, P> ft) {
		this.functions.add(new SourceTargetFunctions(fs, ft));
	}

	public void map() {
		this.functions.forEach((stf) -> {
			// Get source value
			Object aux = stf.fs.apply(null);
			// Map to target
			stf.ft.apply(aux);
			// Restore source value
			stf.fs.apply(aux);
		});
	}

	public void rev() {
		this.functions.forEach((stf) -> {
			// Get source value
			Object aux = stf.ft.apply(null);
			// Map to target
			stf.fs.apply(aux);
			// Restore source value
			stf.ft.apply(aux);
		});
	}

	private static class SourceTargetSuperFunctions<S, T, P> {
		Function<S, Function<P, P>> fs;
		Function<T, Function<P, P>> ft;
		public SourceTargetSuperFunctions(Function<S, Function<P, P>> fs, Function<T, Function<P, P>> ft) {
			this.fs = fs;
			this.ft = ft;
		}
	}
	
	
	private List<SourceTargetSuperFunctions> funcs = new ArrayList<>();

	public <P> void adds(Function<S, Function<P, P>> fs, Function<T, Function<P, P>> ft) {
		this.funcs.add(new SourceTargetSuperFunctions<S, T, P>(fs, ft));
		
	}

	public void maps(S s, T t) {
		this.funcs.forEach((stf) -> {
			// Get source value
			Object aux = ((Function)stf.fs.apply(s)).apply(null);
			// Map to target
			((Function)stf.ft.apply(t)).apply(aux);
			// Restore source value
			((Function)stf.fs.apply(s)).apply(aux);
		});
	}

	public void revs(S s, T t) {
		this.funcs.forEach((stf) -> {
			// Get source value
			Object aux = ((Function)stf.ft.apply(t)).apply(null);
			// Map to target
			((Function)stf.fs.apply(s)).apply(aux);
			// Restore source value
			((Function)stf.ft.apply(t)).apply(aux);
		});
	}


}
