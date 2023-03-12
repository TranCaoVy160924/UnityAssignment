using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyJump : MonoBehaviour
{
    public float jumpForce = 5f; // the force at which the enemy jumps
    public float jumpDelay = 2f; // the delay between jumps
    private Rigidbody2D rb; // the rigidbody component of the enemy

    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        StartCoroutine(Jump());
    }

    IEnumerator Jump()
    {
        while (true)
        {
            // add upward force to the enemy
            rb.AddForce(new Vector2(0f, jumpForce), ForceMode2D.Impulse);
            yield return new WaitForSeconds(jumpDelay);
        }
    }
}