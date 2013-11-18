/*
 * Copyright 2011-2013, by Vladimir Kostyukov and Contributors.
 *
 * This file is part of la4j project (http://la4j.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributor(s): -
 *
 */

package org.la4j.linear;

import org.la4j.LinearAlgebra;
import org.la4j.decomposition.MatrixDecompositor;
import org.la4j.factory.Factory;
import org.la4j.matrix.Matrices;
import org.la4j.matrix.Matrix;
import org.la4j.vector.Vector;
import org.la4j.vector.Vectors;

public class LeastSquaresSolver extends AbstractSolver implements LinearSystemSolver {

    public LeastSquaresSolver(Matrix a) {
        super(a);
    }

    @Override
    public Vector solve(Vector b, Factory factory) {
        ensureRHSIsCorrect(b);
        return solve(new LinearSystem(a, b, factory), factory);
    }

    @Override
    @Deprecated
    public Vector solve(LinearSystem linearSystem, Factory factory) {

        if (!suitableFor(linearSystem)) {
            fail("This system can not be solved with Least Squares Solver.");
        }

        int n = linearSystem.variables();
        int m = linearSystem.equations();

        Matrix a = linearSystem.coefficientsMatrix();
        Vector b = linearSystem.rightHandVector();

        // we use QR for this
        MatrixDecompositor decompositor = a.withDecompositor(LinearAlgebra.RAW_QR);
        Matrix[] qrr = decompositor.decompose(factory);

        // TODO: Do something with it.
        Matrix qr = qrr[0];
        Matrix r = qrr[1];

        // check whether the matrix is full-rank or not
        for (int i = 0; i < r.rows(); i++) {
            if (r.get(i, i) == 0.0) {
                fail("This system can not be solved: coefficient matrix is rank deficient.");
            }
        }

        Vector x = b.copy(factory);

        for (int j = 0; j < n; j++) {

            double acc = 0.0;

            for (int i = j; i < m; i++) {
                acc += qr.get(i, j) * x.get(i);
            }

            acc = -acc / qr.get(j, j);
            for (int i = j; i < m; i++) {
                x.update(i, Vectors.asPlusFunction(acc * qr.get(i, j)));
            }
        }

        for (int j = n - 1; j >= 0; j--) {
            x.update(j, Vectors.asDivFunction(r.get(j, j)));

            for (int i = 0; i < j; i++) {
                x.update(i, Vectors.asMinusFunction(x.get(j) * qr.get(i, j)));
            }
        }

        return x.slice(0, n);
    }

    @Override
    @Deprecated
    public boolean suitableFor(LinearSystem linearSystem) {
        return applicableTo(linearSystem.coefficientsMatrix());
    }

    @Override
    public boolean applicableTo(Matrix matrix) {
        return  matrix.rows() >= matrix.columns();
    }
}
